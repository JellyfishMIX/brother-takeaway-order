package me.jmix.brothertakeaway.order.service;

import me.jmix.brothertakeaway.order.dto.OrderDTO;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO createOrder(OrderDTO orderDTO);
}