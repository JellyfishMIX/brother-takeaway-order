package me.jmix.brothertakeaway.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.dao.OrderDetailRepository;
import me.jmix.brothertakeaway.dao.OrderMasterRepository;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.entity.OrderMaster;
import me.jmix.brothertakeaway.enums.OrderEnum;
import me.jmix.brothertakeaway.enums.PayEnum;
import me.jmix.brothertakeaway.service.OrderService;
import me.jmix.brothertakeaway.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        // 在创建订单的最开始，生成此订单的Id
        String orderId = KeyUtil.getUniqueKey();

        // TODO 查询商品信息（调用商品服务）
        // TODO 计算总价
        // TODO 扣库存（调用商品服务）

        // 3. 写入订单数据库（orderMaster和orderDetail）
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(new BigDecimal(5));
        orderDTO.setOrderStatus(OrderEnum.NEW.getStateCode());
        orderDTO.setPayStatus(PayEnum.WAIT.getStateCode());
        OrderMaster orderMaster = new OrderMaster();
        // 此步不可更换位置，否则会导致BeanUtils.copyProperties把空属性（orderId, orderAmount）拷贝进orderDetail中
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
