package edu.cxy.svspcxy.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Mr·Xiang
 * @create 2024-07-03 17:04
 */
@Data
public class VideoAddVo {
    private String title;
    private MultipartFile cover;    // 文件类型，用来接收前端上传的文件
    private MultipartFile video;
    private Integer[] cids;         // 分类id数组
}
