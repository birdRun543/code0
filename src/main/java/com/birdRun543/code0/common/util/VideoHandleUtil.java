package com.birdRun543.code0.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanbing
 * @date 2020/11/2
 */
@Slf4j
public class VideoHandleUtil {
    /**
     * fetch the first image of the video
     * @param videoFile video path
     * @param saveDir save path
     * @param fileName file name
     * @return Boolean
     */
    public static Boolean saveFirstImage(String videoFile, String saveDir, String fileName) {

        List<String> commands = new ArrayList<>();
        commands.add("ffmpeg");
        commands.add("-ss");
        //这个参数是设置截取视频多少秒时的画面
        commands.add("00:00:01");
        commands.add("-i");
        commands.add(videoFile);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-t");
        commands.add("0.001");
        commands.add("-s");
        //这个参数是设置截取图片的大小
        commands.add("700x525");
        commands.add(saveDir + File.separator + fileName + ".jpg");
        log.debug(commands.toString());
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.redirectErrorStream(true);
            builder = new ProcessBuilder(commands);
            log.info(new Date() + ":开始截取");
            Process process = builder.start();
            int code = process.waitFor();
            log.info(new Date() + ":截取成功" + code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * fetch the time length of the video
     */
    public static String getVideoTime(String videoPath) {
        List<String> commands = new ArrayList<>();
        commands.add("ffmpeg");
        commands.add("-i");
        commands.add(videoPath);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();

            //从视频信息中解析时长
            //            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            String regexDuration = "Duration: ([^.]*)\\.\\d";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(stringBuilder.toString());
            if (m.find()) {
                String timeLenth = m.group(1);
                log.debug(timeLenth);
                return timeLenth;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        VideoHandleUtil.getVideoTime("/tmp/ssssss.mp4");
        VideoHandleUtil.saveFirstImage("/tmp/ssssss.mp4","/tmp","imgfile");
    }
}
