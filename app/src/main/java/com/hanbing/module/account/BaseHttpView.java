package com.hanbing.module.account;

/**
 * Created by hanbing on 2017/3/3
 */

public interface BaseHttpView  {

    void showProgress(String msg);

    void onStarted();

    void onError(int code, String msg);

    void onSuccess(String msg);

    void onCompleted();
}
