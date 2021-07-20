package edu.pdx.cs410J.huidong;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Try {
    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);

        // 定义两个时间
        String startTime = "1/7/2018 07:00 am";
        String endTime = "1/17/2018 05:00 pm";

        // 将两个String类型的时间转换为Date类型，从而计算差值、parse()方法的作用是将String类型的时间解析为Date类型
        Date d1 = df.parse(startTime);
        Date d2 = df.parse(endTime);
        System.out.println(d1.getTime());
        System.out.println(d2.getTime());

        long v1 = d2.getTime() - d1.getTime();
        long days = v1 / (1000 * 60 * 60 * 24);
        System.out.println(v1/(1000*60*60*24));
        System.out.println((((d2.getTime() - d1.getTime()) / 1000) % 60));
        System.exit(0);
    }
}
