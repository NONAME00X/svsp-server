package edu.cxy.svspcxy.entity;

import lombok.Data;

@Data
public class Follow {
    private Integer id;
    private Integer uid;
    private Integer fid;
    private String time;

    //粉丝头像和账号
    private String avatar;
    private String account;

}
