package edu.cxy.svspcxy.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mr·Xiang
 * @create 2024-07-02 15:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private Integer code;   // 响应的状态，参考http协议状态
    private String message; // 消息
    private T data;         // 封装的数据
}
