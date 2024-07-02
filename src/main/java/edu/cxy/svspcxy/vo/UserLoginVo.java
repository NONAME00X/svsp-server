package edu.cxy.svspcxy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * vo：view object 视图对象：用来在前端、后端之间传输数据的对象
 * vo、dto、pojo、entity、model
 * @author: Mr·Xiang
 * @create 2024-07-02 10:50
 */
@Data  // 自动为属性加上对应的getter和setter，同时还重写toString方法
@NoArgsConstructor  // 无参构造器、构造函数   UserLoginVo()
@AllArgsConstructor // 全参构造器     UserLoginVo(String account, String password)
@Accessors(chain = true)  // 链式表达
public class UserLoginVo {
    private String account;
    private String password;
}
