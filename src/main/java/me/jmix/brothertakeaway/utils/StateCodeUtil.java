package me.jmix.brothertakeaway.utils;

import me.jmix.brothertakeaway.enums.StateCodeEnum;

public class StateCodeUtil {
    public static <T extends StateCodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getStateCode())) {
                return each;
            }
        }
        return null;
    }
}
