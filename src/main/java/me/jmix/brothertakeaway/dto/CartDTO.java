package me.jmix.brothertakeaway.dto;

import lombok.Data;

/**
 * @author JellyfishMIX
 * @date 2020/6/18 3:54 下午
 */
@Data
public class CartDTO {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
