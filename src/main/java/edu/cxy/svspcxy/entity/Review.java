package edu.cxy.svspcxy.entity;

import lombok.Data;

/**
 * @author: Mr·Xiang
 * @create 2024-07-06 17:15
 */
@Data
public class Review {
    // 评论信息
    private Integer id;
    private Integer vid;
    private Integer uid;
    private String content;
    private String time;
    private String state;

    // 评论者的信息
    private String account;
    private String avatar;

    //视频的标题
    private String title;
}
