package com.hanbing.module.account.base;

import java.util.Objects;

/**
 * Created by hanbing on 2017/3/7
 */

public interface HttpRequestCallback {

    void onSuccess(Object object);
    void onError(int code, String msg);
}
