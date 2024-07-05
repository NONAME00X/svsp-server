package edu.cxy.svspcxy.config;

import edu.cxy.svspcxy.entity.Video;
import edu.cxy.svspcxy.service.VideoService;
import edu.cxy.svspcxy.util.OssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling    //开启定时任务
public class VideoTask {
	@Resource
	private VideoService videoService;

	@Resource
	private OssUtil greenVideoUtil;
    //指定策略 每隔30秒执行一次
	@Scheduled(cron="0/30 * * * * ?")
	public void task() {
		log.debug("执行周期任务：{}",new Date());

		// 查询状态是video_commit的视频
		List<Video> videoList = videoService.findByStateCommit();
		videoList.forEach(video -> {
			String reason = null;
			if((reason = greenVideoUtil.checkState(video.getTaskid())) != null){
				video.setReason(reason);
				videoService.updateState(video);
			}
		});
	}
}