package edu.cxy.svspcxy.entity;

import lombok.Data;

/**
 * @author: Mr·Xiang
 * @create 2024-07-03 11:07
 */
@Data
public class Video {
    private Integer id;
    private Integer uid;
    private String title;
    private String video;   // 视频的URL
    private String cover;   // 封面
    private String uptime;  // 上传时间
    private Integer playnums;   // 播放次数
    private String reason;      // 是否通过的信息
    private String state;   // 视频状态
    private String taskid;  // 任务id

    private Integer[] cids; // 分类id
}
