package edu.cxy.svspcxy.config;

import edu.cxy.svspcxy.util.OssUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Mr·Xiang
 * @create 2024-06-25 15:59
 */
@Data
@Configuration
public class OssConfig {
    // @Value可以用来获取配置文件中的配置信息
    @Value("${alibaba.cloud.access-key}")
    private String accessKey;
    @Value("${alibaba.cloud.secret-key}")
    private String secret;
    @Value("${alibaba.cloud.region}")
    private String region;
    @Value("${alibaba.cloud.endpoint}")
    private String endpoint;

    @Bean
    public OssUtil ossUtil(){
        return new OssUtil(accessKey, secret, region, endpoint);
    }
}
