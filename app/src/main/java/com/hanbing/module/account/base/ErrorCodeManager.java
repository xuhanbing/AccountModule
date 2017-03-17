package com.hanbing.module.account.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

/**
 * Created by hanbing on 2017/3/6
 */

public class ErrorCodeManager {


    static ErrorCodeManager INSTANCE = new ErrorCodeManager();

    public static ErrorCodeManager getInstance() {
        return INSTANCE;
    }

    SparseArray<String> mErrors = new SparseArray<>();


    public void init(Context context) {
        add(ErrorCode.OK, "Success.");

        add(ErrorCode.USERNAME_NO_EMPTY, "No empty username.");
        add(ErrorCode.USERNAME_EXIST, "Username exist.");
        add(ErrorCode.USERNAME_NOT_EXIST, "Username not exist.");
        add(ErrorCode.USERNAME_INVALID, "Username not invalid.");
        add(ErrorCode.USERNAME_OR_PWD_NO_EMPTY, "Username or password no empty.");
        add(ErrorCode.USERNAME_LENGTH_INVALID, "Username length must > 3.");

        add(ErrorCode.PWD_NO_EMPTY, "No empty password.");
        add(ErrorCode.PWD_INVALID, "Password invalid.");
        add(ErrorCode.PWD_SAME, "New password is same as old one.");
        add(ErrorCode.PWD_NOT_SAME, "Password not match.");
        add(ErrorCode.PWD_ERROR, "Password error.");


        add(ErrorCode.MOBILE_NO_EMPTY, "No empty mobile.");
        add(ErrorCode.MOBILE_INVALID, "Invalid mobile.");
        add(ErrorCode.MOBILE_NOT_MATCH, "Not match request mobile.");
        add(ErrorCode.MOBILE_EXIST, "Mobile has been registered.");
        add(ErrorCode.MOBILE_NOT_EXIST, "Mobile has not been registered.");



        add(ErrorCode.VERIFY_CODE_NO_EMPTY, "No empty verification code.");
        add(ErrorCode.VERIFY_CODE_LENGTH_INVALID, "Verification code must be = 6.");
        add(ErrorCode.VERIFY_CODE_ERROR, "Verification code error.");

//        add(ErrorCode.OK, "Success.");
//        add(ErrorCode.OK, "Success.");
//        add(ErrorCode.OK, "Success.");

    }


    final void add(int code, String msg) {
        if (null == mErrors)
            mErrors = new SparseArray<>();
        mErrors.put(code, msg);
    }

    public String parse(int code) {
        return parse(code, "Error (" + code + ").");
    }

    public String parse(int code, String dftMsg) {
        String msg = null == mErrors ? null : mErrors.get(code);

        if (TextUtils.isEmpty(msg))
            return dftMsg;

        return msg;
    }


    public static String parseCode(int code) {
        return getInstance().parse(code);
    }

    public static String parseCode(int code, String dftMsg) {
        return getInstance().parse(code, dftMsg);
    }

}
