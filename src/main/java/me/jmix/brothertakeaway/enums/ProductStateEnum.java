package me.jmix.brothertakeaway.enums;

import lombok.Getter;

/**
 * @author JellyfishMIX
 * @date 2020/6/1 9:16 下午
 */
@Getter
public enum ProductStateEnum {
    SHELVES(0, "上架"),
    DISCONTINUED(1, "下架");

    private Integer stateCode;
    private String stateInfo;

    ProductStateEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
