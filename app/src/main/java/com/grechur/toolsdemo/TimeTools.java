package com.grechur.toolsdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: ToolsDemo
 * @Package: com.grechur.toolsdemo
 * @ClassName: TimeTools
 * @Description: 时间转换工具
 * @Author: Grechur
 * @CreateDate: 2020/3/11 10:33
 * @UpdateUser: Grechur
 * @UpdateDate: 2020/3/11 10:33
 */
public class TimeTools {
    public static final long DAY_TO_MILL = 86400;//24*3600 一天转换的秒数
    public static final long HOUR_TO_MILL = 3600;//60*60 一小时转换的秒数
    /**
     * 格式化时间
     */
    public static String convertTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        String t = format.format(d1);
        return t;
    }

    public static String stringToDay(long time){
        String times = "";
        long secondMill = time/1000;
        int day = (int) (secondMill/DAY_TO_MILL);
        int hour = (int) (secondMill%DAY_TO_MILL/HOUR_TO_MILL);
        int second = (int) ((secondMill%DAY_TO_MILL%HOUR_TO_MILL)/60);
        int mill = (int) (secondMill%60);
        times = day+"天"+hour+"时"+second+"分"+mill+"秒";
        return times;
    }
}
