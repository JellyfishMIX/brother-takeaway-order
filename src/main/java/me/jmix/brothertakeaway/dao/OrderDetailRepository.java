package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author JellyfishMIX
 * @date 2020/6/4 10:30 下午
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

}
