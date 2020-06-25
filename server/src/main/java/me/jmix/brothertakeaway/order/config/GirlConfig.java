package me.jmix.brothertakeaway.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author JellyfishMIX
 * @date 2020/6/26 2:19 上午
 */
@Data
@Component
@ConfigurationProperties("girl")
@RefreshScope
public class GirlConfig {
    private String name;
    private Integer age;
}
