package me.jmix.brothertakeaway.entity;

import lombok.Data;
import me.jmix.brothertakeaway.enums.ProductStateEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JellyfishMIX
 * @date 2020/6/1 7:17 下午
 */
@Data
@Table(name = "tb_product_info")
@Entity
public class ProductInfo {
    /**
     * 类目Id
     */
    @Id
    private String productId;
    /**
     * 名字
     */
    private String productName;
    /**
     * 单价
     */
    private BigDecimal productPrice;
    /**
     * 库存
     */
    private Integer productStock;
    /**
     * 商品描述
     */
    private String productDescription;
    /**
     * 商品小图
     */
    private String productIcon;
    /**
     * 商品状态，0上架，1下架
     */
    private Integer productStatus = ProductStateEnum.SHELVES.getStateCode();
    /**
     * 类目编号
     */
    private Integer categoryType;
    /**
     * 创建时间，自动写入
     */
    private Date createTime;
    /**
     * 修改时间，自动写入
     */
    private Date updateTime;
}

