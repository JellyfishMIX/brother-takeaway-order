package me.jmix.brothertakeaway.order.exception;

import lombok.Getter;
import me.jmix.brothertakeaway.order.enums.OrderEnum;
import me.jmix.brothertakeaway.order.enums.ResultEnum;

@Getter
public class OrderException extends RuntimeException {
    private static final long serialVersionUID = 7041929752847179080L;

    private Integer stateCode;
    private String stateInfo;

    public OrderException(OrderEnum orderEnum) {
        super(orderEnum.getStateMessage());
        this.stateCode = orderEnum.getStateCode();
        this.stateInfo = orderEnum.getStateMessage();
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.stateCode = resultEnum.getCode();
        this.stateInfo = resultEnum.getMessage();
    }

    public OrderException(Integer stateCode, String errMsg) {
        super(errMsg);
        this.stateCode = stateCode;
        this.stateInfo = errMsg;
    }
}
