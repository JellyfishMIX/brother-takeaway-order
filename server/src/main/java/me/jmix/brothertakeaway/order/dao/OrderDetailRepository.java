package me.jmix.brothertakeaway.order.dao;

import me.jmix.brothertakeaway.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author JellyfishMIX
 * @date 2020/6/4 10:30 下午
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
