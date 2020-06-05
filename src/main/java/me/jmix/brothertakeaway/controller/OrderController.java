package me.jmix.brothertakeaway.controller;

import lombok.extern.slf4j.Slf4j;
import me.jmix.brothertakeaway.converter.OrderFormToOrderDTOConverter;
import me.jmix.brothertakeaway.dto.OrderDTO;
import me.jmix.brothertakeaway.enums.OrderEnum;
import me.jmix.brothertakeaway.exception.OrderException;
import me.jmix.brothertakeaway.form.CustomerOrderForm;
import me.jmix.brothertakeaway.service.OrderService;
import me.jmix.brothertakeaway.utils.ResultVOUtil;
import me.jmix.brothertakeaway.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JellyfishMIX
 * @date 2020/6/5 5:11 下午
 */
@Slf4j
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
}
