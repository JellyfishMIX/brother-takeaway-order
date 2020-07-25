package me.jmix.brothertakeaway.order.enums;

import lombok.Getter;

@Getter
public enum OrderEnum implements StateCodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),

    FORM_PARAM_ERROR(-1001, "表单参数不正确"),
    JSON_CONVERT_ERROR(-1002, "JSONToObject转换错误"),
    SHOPPING_CART_EMPTY(-1003, "购物车为空"),
    CUSTOMER_OPENID_INCONSISTENT(-1004, "订单openid不一致"),
    ORDER_STATUS_ERROR(1005, "订单状态不正确");

    private Integer stateCode;
    private String stateMessage;

    OrderEnum(Integer stateCode, String stateMessage) {
        this.stateCode = stateCode;
        this.stateMessage = stateMessage;
    }
}
