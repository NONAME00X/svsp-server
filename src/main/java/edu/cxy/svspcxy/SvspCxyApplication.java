package edu.cxy.svspcxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket   // 开启websocket服务
public class SvspCxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvspCxyApplication.class, args);
    }

}
