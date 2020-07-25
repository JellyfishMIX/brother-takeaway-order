package me.jmix.brothertakeaway.order.controller;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.order.converter.OrderFormToOrderDTOConverter;
import me.jmix.brothertakeaway.order.dto.OrderDTO;
import me.jmix.brothertakeaway.order.enums.OrderEnum;
import me.jmix.brothertakeaway.order.exception.OrderException;
import me.jmix.brothertakeaway.order.form.CustomerOrderForm;
import me.jmix.brothertakeaway.order.service.OrderService;
import me.jmix.brothertakeaway.order.utils.ResultVOUtil;
import me.jmix.brothertakeaway.order.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JellyfishMIX
 * @date 2020/6/5 5:11 下午
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> createOrder(@Valid CustomerOrderForm customerOrderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确，customerOrderForm = {}", customerOrderForm);
            throw new OrderException(OrderEnum.FORM_PARAM_ERROR.getStateCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(customerOrderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车不能为空，customerOrderForm = {}", customerOrderForm);
            throw new OrderException(OrderEnum.SHOPPING_CART_EMPTY);
        }
        OrderDTO orderDTOResult = orderService.createOrder(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDTOResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    /**
     * 完结订单（只能卖家端操作）
     *
     * @return
     */
    @PostMapping("/finish")
    public ResultVO finish(@RequestParam("orderId") String orderId) {
        return ResultVOUtil.success(orderService.finish(orderId));
    }
}
