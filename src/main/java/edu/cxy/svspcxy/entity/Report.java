package edu.cxy.svspcxy.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.bouncycastle.cms.PasswordRecipient;

@Data
public class Report {
    private Integer id;
    private Integer uid;
    private Integer vid;
    private String reason;
    private String state;
    private String time;

    //投诉视频
    private String title;
    private String account;

}
