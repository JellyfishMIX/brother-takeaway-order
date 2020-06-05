package me.jmix.brothertakeaway.vo;

import lombok.Data;

/**
 * @author JellyfishMIX
 * @date 2020/4/8 9:25 下午
 * 返回给前端的VO
 */
@Data
public class ResultVO<T> {
    /**
     * 表示异常的ResultVO需要使用此成员变量
     */
    private String exceptionClassName;

    private Integer code;
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
