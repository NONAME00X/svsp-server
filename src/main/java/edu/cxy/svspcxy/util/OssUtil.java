package edu.cxy.svspcxy.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.green20220302.Client;
import com.aliyun.green20220302.models.*;
import com.aliyun.oss.OSSClient;
import com.aliyun.teaopenapi.models.Config;
import edu.cxy.svspcxy.service.VideoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: Mr·Xiang
 * @create 2024-06-25 14:56
 */
@Data
@Slf4j
public class OssUtil {
    // 连接参数
    @Resource
    private VideoService videoService;

    @Value("${alibaba.cloud.oss.bucketName}")
    private String bucketName;

    @Resource
    private OSSClient ossClient;

    // 任务线程池
    private final ExecutorService EXECUTORSERVICE = Executors.newFixedThreadPool(100);
    private Client CLIENT;

    public OssUtil(String accessKey, String secret, String region, String endpoint){
        // 初始化配置
        Config config = new Config();
        config.setAccessKeyId(accessKey);
        config.setAccessKeySecret(secret);
        //接入区域和地址请根据实际情况修改。
        config.setRegionId(region);
        config.setEndpoint(endpoint);
        //连接时超时时间，单位毫秒（ms）。
        config.setReadTimeout(6000);
        //读取时超时时间，单位毫秒（ms）。
        config.setConnectTimeout(3000);
        try {
            CLIENT = new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 审核视频任务类
    private class CheckVideoRunnable implements Runnable{
        private String url; // 视频URL
        private Integer id; // 视频id
        public CheckVideoRunnable(String url, Integer id) {
            this.url = url;
            this.id = id;
        }

        @Override
        public void run() {
            JSONObject serviceParameters = new JSONObject();
            serviceParameters.put("url", url);
            VideoModerationRequest videoModerationRequest = new VideoModerationRequest();
            // 检测类型：videoDetection
            videoModerationRequest.setService("videoDetection");
            videoModerationRequest.setServiceParameters(serviceParameters.toJSONString());
            try {
                VideoModerationResponse response = CLIENT.videoModeration(videoModerationRequest);
                if (response.getStatusCode() == 200) {
                    VideoModerationResponseBody result = response.getBody();
                    log.info(JSON.toJSONString(result));
                    log.info("requestId = " + result.getRequestId());
                    log.info("code = " + result.getCode());
                    log.info("msg = " + result.getMessage());
                    Integer code = result.getCode();
                    if (200 == code) {
                        VideoModerationResponseBody.VideoModerationResponseBodyData data = result.getData();
                        // 将任务id写入数据库
                        videoService.addTaskId(id, data.getTaskId());
                        
                        log.info("taskId = [" + data.getTaskId() + "]");
                    } else {
                        log.info("video moderation not success. code:" + code);
                    }
                } else {
                    log.info("response not success. status:" + response.getStatusCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查视频
     *
     * @param url 视频地址
     * @param id  视频id
     */
    public void check(String url, Integer id){
        EXECUTORSERVICE.execute(new CheckVideoRunnable(url, id));
    }

    /**
     * 通过任务id获取校验结果
     * @param taskId
     * @return
     */
    public String checkState(String taskId){
        JSONObject serviceParameters = new JSONObject();
        // 提交任务时返回的taskId。
        serviceParameters.put("taskId", taskId);
        VideoModerationResultRequest videoModerationResultRequest = new VideoModerationResultRequest();
        // 检测类型：videoDetection
        videoModerationResultRequest.setService("videoDetection");
        videoModerationResultRequest.setServiceParameters(serviceParameters.toJSONString());

        try {
            VideoModerationResultResponse response = CLIENT.videoModerationResult(videoModerationResultRequest);
            if (response.getStatusCode() == 200) {
                VideoModerationResultResponseBody result = response.getBody();
                log.info("requestId=" + result.getRequestId());
                log.info("code=" + result.getCode());
                log.info("msg=" + result.getMessage());
                if (200 == result.getCode()) {
                    VideoModerationResultResponseBody.VideoModerationResultResponseBodyData data = result.getData();
                    log.info("dataId = " + data.getDataId());
                    log.info("audioResult = " + JSON.toJSONString(data.getAudioResult()));
                    log.info("frameResult = " + JSON.toJSONString(data.getFrameResult()));
                    // 获取结果
                    String label = data.getFrameResult().getFrames().get(0).getResults().get(0).getResult().get(0).getLabel();
                    return label;
                } else {
                    log.info("video moderation result not success. code:" + result.getCode());
                }
            } else {
                log.info("response not success. status:" + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String upOss(MultipartFile file) throws IOException {
        InputStream inputStream = null;
        String fileName = file.getOriginalFilename();
        // 上传视频
        inputStream = file.getInputStream();
        ossClient.putObject(bucketName, fileName, inputStream);
        //设置文件有效期  60天
        Date date = new Date(new Date().getTime()+ 1000 * 3600 * 24 * 60);
        //获取url
        URL url = ossClient.generatePresignedUrl(bucketName, fileName,date);
        // 获取文件路径
        return url.toString();
    }
}
