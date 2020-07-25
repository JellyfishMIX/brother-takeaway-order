package me.jmix.brothertakeaway.order.service;

import me.jmix.brothertakeaway.order.dto.OrderDTO;

public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO orderDTO
     * @return
     */
    OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * 完结订单（只能卖家操作）
     *
     * @param orderId 订单编号
     * @return
     */
    OrderDTO finish(String orderId);
}