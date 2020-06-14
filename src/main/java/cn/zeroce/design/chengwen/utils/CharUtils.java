package cn.zeroce.design.chengwen.utils;

import java.util.Random;

/**
 * @author: zeroce
 * @date 20.4.11 18:30
 */
public class CharUtils {
    public static String getRandomString(Integer number) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < number; i++) {
            int nextNumber = random.nextInt(base.length());
            sb.append(base.charAt(nextNumber));
        }
        return sb.toString();
    }

    public static String getRandomNum(Integer number) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < number; i++) {
            int nextNumber = random.nextInt(base.length());
            sb.append(base.charAt(nextNumber));
        }
        return sb.toString();
    }
}
