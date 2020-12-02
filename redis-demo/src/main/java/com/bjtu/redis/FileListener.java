package com.bjtu.redis;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author <a href="Tastill@**.cn">Tastill</a>
 * @version 2019/1/24 14:55
 * @description FileListener
 */
public class FileListener extends FileAlterationListenerAdaptor {
    public static final Logger logger = Logger.getLogger(FileListener.class);
    @Override
    public void onStart(FileAlterationObserver observer) {

        super.onStart(observer);
       // System.out.println("Monitor has started!");
    }
    @Override
    public void onFileCreate(File file) {
        logger.info("有新文件生成：" + file.getName());
    }
    @Override
    public void onFileChange(File file) {
        logger.info("有文件被修改：" + file.getName());
        System.out.println("Some files have been modified:" + file.getName());
        System.out.println("Reloading configuration file...");
        if (file.getName().equals("Counter.json") ) {
            RedisDemoApplication.readCounterConfig();
        } else if (file.getName().equals("Action.json") ) {
            RedisDemoApplication.readActionConfig();
        }
        System.out.println("Configuration file has been reloaded.");
    }

    @Override
    public void onFileDelete(File file) {
        logger.info("有文件被删除：" + file.getName());
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        //System.out.println("监听停止");
        super.onStop(observer);
    }
}