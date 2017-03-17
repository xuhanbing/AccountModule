package com.hanbing.module.account;

import android.os.Handler;
import android.text.TextUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hanbing on 2017/3/3
 */

public class Utils {


    static Handler mHandler = new Handler();

    public static void queueTask(Runnable r) {
        queueTask(r, 1500);
    }

    public static void queueTask(Runnable r, long delay) {
        if (delay >= 0) {
            mHandler.postDelayed(r, delay);
        } else {
            mHandler.post(r);
        }
    }



    public static boolean equals(Object a, Object b) {
        return (a == b) || (null != a && a.equals(b));
    }

    public static boolean isMobile(String mobile) {

        if (TextUtils.isEmpty(mobile))
            return false;

        Pattern pattern = Pattern.compile("[1][35789][0-9]{9}");

        Matcher matcher = pattern.matcher(mobile);

        return matcher.find();
    }

    public static boolean isValidUsername(String username) {
        return !TextUtils.isEmpty(username) && username.length() > 3;
    }

    public static boolean isValidPwd(String pwd) {
        return !TextUtils.isEmpty(pwd);
    }
}
