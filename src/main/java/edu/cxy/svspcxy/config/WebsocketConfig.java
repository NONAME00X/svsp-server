package edu.cxy.svspcxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author: Elivs.Xiang
 * @Email: 406862257@qq.com
 * @create 2022-12-06 15:09
 * @verson IDEA 2020.3
 */

@Configuration
public class WebsocketConfig {
    @Bean    //在容器中创建bean对象，在WebSocketUtil中需要用到的RemoteEndpoint对象
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}