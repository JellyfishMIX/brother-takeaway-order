package me.jmix.brothertakeaway.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间 + 随机数
     * 高并发情况下可能会出现重名，所以需要使用synchronized关键词来修饰
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer randomNumber = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(randomNumber);
    }
}
