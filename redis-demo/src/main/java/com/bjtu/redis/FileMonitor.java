package com.bjtu.redis;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:Tastill@**.cn">Tastill</a>
 * @version 2019/1/24 15:00
 * @description FileMonitorTest
 */
@Component
public class FileMonitor {
    /**
     * @param
     * @return
     * @description
     * @version 2.0, 2019/1/25 9:44
     * @author <a href="mailto:Tastill@**.cn">Tastill</a>
     */
    @PostConstruct
    public void initFileMonitor(String fileName) {
        // 监控目录
        String rootDir =FileMonitor.class.getClassLoader().getResource("").getPath();
      //  String rootDir = Objects.requireNonNull(FileMonitor.class.getClassLoader().get).getPath();;
        // 轮询间隔 5 秒

        //System.out.println("------"+new File(String.valueOf(rootDir)).getAbsolutePath());
        Integer time = 1;
        long interval = TimeUnit.SECONDS.toMillis(time);
        FileAlterationObserver observer = new FileAlterationObserver(rootDir);
        observer.addListener(new FileListener()); //设置文件变化监听器
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);
        // 开始监控
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}