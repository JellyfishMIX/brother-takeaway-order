package me.jmix.brothertakeaway.order.entity;

import lombok.Data;
import me.jmix.brothertakeaway.order.enums.OrderEnum;
import me.jmix.brothertakeaway.order.enums.PayEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tb_order_master")
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class OrderMaster {
    /**
     * 订单Id
     */
    @Id
    private String orderId;

    /**
     * 顾客名字
     */
    private String customerName;

    /**
     * 顾客手机号
     */
    private String customerPhone;

    /**
     * 顾客地址
     */
    private String customerAddress;

    /**
     * 顾客微信openid
     */
    private String customerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认为0: 新下单
     */
    private Integer orderStatus = OrderEnum.NEW.getStateCode();

    /**
     * 支付状态，默认为0: 未支付
     */
    private Integer payStatus = PayEnum.WAIT.getStateCode();

    /**
     * 创建时间，自动写入
     */
    private Date createTime;

    /**
     * 修改时间，自动写入
     */
    private Date updateTime;
}
