package com.hanbing.module.account.base;

import com.hanbing.module.account.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanbing on 2017/3/6
 */

public class AccountManager {


    static AccountManager INSTANCE = new AccountManager();

    public static AccountManager getInstance() {
        return INSTANCE;
    }


    Map<String, String> mAccounts = new HashMap<>();

    public int login(String username, String pwd) {

        if (!mAccounts.containsKey(username)) {
            return ErrorCode.USERNAME_NOT_EXIST;
        } else {
            String cachePwd = mAccounts.get(username);

            if (Utils.equals(cachePwd, pwd)) {
                return ErrorCode.OK;
            } else{
                return ErrorCode.PWD_ERROR;
            }
        }
    }

    public int register(String username, String pwd) {
        if (mAccounts.containsKey(username)) {
            return ErrorCode.USERNAME_EXIST;
        } else {
            mAccounts.put(username, pwd);

            return ErrorCode.OK;
        }
    }

    public int resetPwd(String username, String oldPwd, String newPwd) {


        if (!mAccounts.containsKey(username))
            return ErrorCode.USERNAME_NOT_EXIST;

        if (Utils.equals(oldPwd, newPwd))
            return ErrorCode.PWD_SAME;


        String cachePwd = mAccounts.get(username);

        if (Utils.equals(cachePwd, oldPwd)) {
            mAccounts.put(username, newPwd);
            return ErrorCode.OK;
        } else{
            return ErrorCode.PWD_ERROR;
        }
    }

    public int setPwd(String username, String pwd) {
        if (!mAccounts.containsKey(username))
            return ErrorCode.USERNAME_NOT_EXIST;

        mAccounts.put(username, pwd);

        return ErrorCode.OK;
    }

    public boolean has(String username) {
        return mAccounts.containsKey(username);
    }
}
