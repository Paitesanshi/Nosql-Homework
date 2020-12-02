package com.bjtu.redis;

import org.springframework.boot.autoconfigure.session.SessionProperties;
import redis.clients.jedis.Jedis;
import sun.print.PSPrinterJob;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class workCounter {


    public static void work(Counter counter) {
        switch (counterType.valueOf(counter.getName())) {

            case showuserNUM:
                showuser(counter);
                break;
            case incrUser:
                incruser(counter);
                break;
            case decrUser:
                decruser(counter);
                break;
            case showuserInFreq:
                showuerInFreq(counter);
                break;
            case showuserOutFreq:
                showuerOutFreq(counter);
                break;
        }
    }

    private static void decruser(Counter decr) {
        String key = decr.getKey().get(0);
        String list=decr.getKey().get(1);
        RedisUtil redisUtil = new RedisUtil();
        try {
            redisUtil.decr(key, decr.getValue());
            System.out.println("The value of " + key + " decreased by " + decr.getValue() + " and became " + redisUtil.get(key));
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String string=time.format(date);
            redisUtil.lpush(list,string);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    private static void showuser(Counter c) {
        String key = c.getKey().get(0);
        RedisUtil redisUtil = new RedisUtil();
        try {
            System.out.println("The value of " + key + " is " + redisUtil.get(key));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

    private static void incruser(Counter incr) {
        String key = incr.getKey().get(0);
        String list=incr.getKey().get(1);
        RedisUtil redisUtil = new RedisUtil();
        try {
            redisUtil.incr(key, incr.getValue());
            System.out.println("The value of " + key + " increased by " + incr.getValue() + " and became " + redisUtil.get(key));
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String string=time.format(date);
            redisUtil.lpush(list,string);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void showuerInFreq(Counter counter){
        String keyField=counter.getKey().get(0);
        RedisUtil redisUtil=new RedisUtil();
        try{
            String date=counter.getFREQ();
            String startTime=date.substring(0,12);
            String endTime=date.substring(13,25);
            for (int i = 0; i < redisUtil.llen(keyField); i++) {
           String t=redisUtil.lindex(keyField,i);
                if(t.compareTo(startTime)>=0 && t.compareTo(endTime)<=0){
                    //可以输出此用户元素
                    System.out.println("有用户在"+t+"时刻进入");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showuerOutFreq(Counter counter){
        String keyField=counter.getKey().get(0);
        RedisUtil redisUtil=new RedisUtil();
        try{
            String date=counter.getFREQ();
            String startTime=date.substring(0,12);
            String endTime=date.substring(13,25);
            for (int i = 0; i < redisUtil.llen(keyField); i++) {
//            System.out.println(jedis.lindex("UserOutList",i));
                if(redisUtil.lindex(keyField,i).compareTo(startTime)>=0 && redisUtil.lindex(keyField,i).compareTo(endTime)<=0){
                    //可以输出此用户元素
                    System.out.println("有用户在"+redisUtil.lindex(keyField,i)+"时刻退出");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
