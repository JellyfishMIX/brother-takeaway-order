package me.jmix.brothertakeaway.order.enums;

import lombok.Getter;

@Getter
public enum PayEnum implements StateCodeEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer stateCode;
    private String stateInfo;

    PayEnum(Integer stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }
}
