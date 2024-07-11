package edu.cxy.svspcxy.entity;

import lombok.Data;

@Data
public class Collect {
    private Integer id;
    private Integer vid;
    private Integer uid;
    private String time;

    //视频title和up主头像
    private String title;
    private String avatar;
}
