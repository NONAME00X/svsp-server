package edu.cxy.svspcxy.entity;

import lombok.Data;

/**
 * @author: Mr·Xiang
 * @create 2024-07-02 11:44
 */
@Data
public class User {
    private Integer id;
    private String account;
    private String password;
    private String avatar;
    private String state;
}
