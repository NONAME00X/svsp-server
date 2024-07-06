package edu.cxy.svspcxy.entity;

import lombok.Data;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 9:10
 */
@Data
public class Danmu {
    private Integer id;
    private Integer vid;
    private Integer uid;
    private Integer duration;
    private Integer start;
    private String txt;
    private String mode;
    private String color;
}
