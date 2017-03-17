package com.hanbing.module.account.register;

import android.text.TextUtils;

import com.hanbing.module.account.Utils;
import com.hanbing.module.account.base.AccountManager;
import com.hanbing.module.account.base.ErrorCode;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.base.HttpService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanbing on 2017/3/6
 */

public class RegisterPresenter implements RegisterContract.Presenter {


    RegisterContract.View mView;

    //
    Map<String, String> mAccounts = new HashMap<>();

    HttpService.Task mTask;


    public RegisterPresenter(RegisterContract.View view) {
        assert null != view;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void registerByUsername() {
        String username = mView.getUsername();
        String pwd = mView.getPwd();
        String pwdConfirm = mView.getPwdConfirm();


        int code = ErrorCode.OK;
        if (TextUtils.isEmpty(username)) {
            code = ErrorCode.USERNAME_NO_EMPTY;
        } else if (!Utils.isValidUsername(username)) {
            code = ErrorCode.USERNAME_INVALID;
        }
        else if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdConfirm)) {
            code = ErrorCode.PWD_NO_EMPTY;
        }
        else if (!Utils.isValidPwd(pwd) || !Utils.isValidPwd(pwdConfirm)) {
            code = ErrorCode.PWD_INVALID;
        } else if (!Utils.equals(pwd, pwdConfirm)) {
            code = ErrorCode.PWD_NOT_SAME;
        }

        if (ErrorCode.OK != code) {
            mView.showRegisterError(ErrorCodeManager.parseCode(code));
            return;
        }


        mView.showRegisterStarted();

        Utils.queueTask(() -> {

            int code1 = AccountManager.getInstance().register(username, pwd);

            if (ErrorCode.OK == code1) {
                mView.showRegisterSuccess();
            } else {
                mView.showRegisterError(ErrorCodeManager.parseCode(code1));
            }

            mView.showRegisterCompleted();
        });

    }

    @Override
    public void registerByMobile() {
        String username = mView.getUsername();
        String pwd = mView.getPwd();
        String pwdConfirm = mView.getPwdConfirm();


        int code = ErrorCode.OK;
        if (TextUtils.isEmpty(username)) {
            code = ErrorCode.MOBILE_NO_EMPTY;
        } else if (!Utils.isMobile(username)) {
            code = ErrorCode.MOBILE_INVALID;
        }
        else if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdConfirm)) {
            code = ErrorCode.PWD_NO_EMPTY;
        }
        else if (!Utils.isValidPwd(pwd) || !Utils.isValidPwd(pwdConfirm)) {
            code = ErrorCode.PWD_INVALID;
        } else if (!Utils.equals(pwd, pwdConfirm)) {
            code = ErrorCode.PWD_NOT_SAME;
        }

        if (ErrorCode.OK != code) {
            mView.showRegisterError(ErrorCodeManager.parseCode(code));
            mView.showRegisterCompleted();
            return;
        }


        mView.showRegisterStarted();

        Utils.queueTask(() -> {

            int code1 = AccountManager.getInstance().register(username, pwd);

            if (ErrorCode.OK == code1) {
                mView.showRegisterSuccess();
            } else {
                mView.showRegisterError(ErrorCodeManager.parseCode(code1));
            }

            mView.showRegisterCompleted();
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        cancelTask();
        mView = null;
    }

    private void cancelTask() {
        if (null != mTask) mTask.cancel();
        mTask = null;
    }
}
