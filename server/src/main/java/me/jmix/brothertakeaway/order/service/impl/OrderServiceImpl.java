package me.jmix.brothertakeaway.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.order.dto.OrderDTO;
import me.jmix.brothertakeaway.order.entity.OrderDetail;
import me.jmix.brothertakeaway.order.entity.OrderMaster;
import me.jmix.brothertakeaway.order.enums.PayEnum;
import me.jmix.brothertakeaway.order.enums.ResultEnum;
import me.jmix.brothertakeaway.order.exception.OrderException;
import me.jmix.brothertakeaway.order.service.OrderService;
import me.jmix.brothertakeaway.order.utils.KeyUtil;
import me.jmix.brothertakeaway.product.client.ProductClient;
import me.jmix.brothertakeaway.order.dao.OrderDetailRepository;
import me.jmix.brothertakeaway.order.dao.OrderMasterRepository;
import me.jmix.brothertakeaway.product.entity.ProductInfo;
import me.jmix.brothertakeaway.order.enums.OrderEnum;
import me.jmix.brothertakeaway.product.vo.CartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Resource
    private ProductClient productClient;

    /**
     * 创建订单
     * @param orderDTO orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        // 在创建订单的最开始，生成此订单的Id
        String orderId = KeyUtil.getUniqueKey();

        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForProduct(productIdList);

        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    // 单价 * 数量
                    orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail, "createTime", "updateTime");
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    orderDetail.setOrderId(orderId);
                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣库存（调用商品服务）
        List<CartVO> cartVOList = orderDTO.getOrderDetailList().stream().map(e -> new CartVO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartVOList);

        // 写入订单数据库（orderMaster和orderDetail）
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderStatus(OrderEnum.NEW.getStateCode());
        orderDTO.setPayStatus(PayEnum.WAIT.getStateCode());
        OrderMaster orderMaster = new OrderMaster();
        // 此步不可更换位置，否则会导致BeanUtils.copyProperties把空属性（orderId, orderAmount）拷贝进orderDetail中
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

    /**
     * 完结订单（只能卖家操作）
     *
     * @param orderId 订单编号
     * @return
     */
    @Override
    public OrderDTO finish(String orderId) {
        // 1. 先查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2. 判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!OrderEnum.NEW.getStateCode().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(OrderEnum.ORDER_STATUS_ERROR);
        }

        // 3. 修改订单状态为完结
        orderMaster.setOrderStatus(OrderEnum.FINISHED.getStateCode());
        orderMasterRepository.save(orderMaster);

        // 4. 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
