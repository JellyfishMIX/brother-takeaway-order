package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author JellyfishMIX
 * @date 2020/6/4 10:28 下午
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
