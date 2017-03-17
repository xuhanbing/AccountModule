package com.hanbing.module.account.forget;

import android.text.TextUtils;

import com.hanbing.module.account.Utils;
import com.hanbing.module.account.base.AccountManager;
import com.hanbing.module.account.base.ErrorCode;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.base.HttpRequestCallback;
import com.hanbing.module.account.base.HttpService;

import javax.inject.Inject;

/**
 * Created by hanbing on 2017/3/6
 */

public class ForgetPwdPresenter implements ForgetPwdContract.Presenter {


    ForgetPwdContract.View mView;

    @Inject
    HttpService mHttpService;

    HttpService.Task mTask;

    @Inject
    public ForgetPwdPresenter(ForgetPwdContract.View view) {
        assert null != view;
        mView = view;
    }

    @Inject
    public void setListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void forget() {


        String username = mView.getUsername();
        String pwd = mView.getPwd();
        String pwdConfirm = mView.getPwdConfirm();


        int code = ErrorCode.OK;
        if (TextUtils.isEmpty(username)) {
            code = ErrorCode.USERNAME_NO_EMPTY;
        } else if (!Utils.isValidUsername(username)) {
            code = ErrorCode.USERNAME_INVALID;
        } else if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdConfirm)) {
            code = ErrorCode.PWD_NO_EMPTY;
        } else if (!Utils.isValidPwd(pwd) || !Utils.isValidPwd(pwdConfirm)) {
            code = ErrorCode.PWD_INVALID;
        } else if (!Utils.equals(pwd, pwdConfirm)) {
            code = ErrorCode.PWD_NOT_SAME;
        }

        if (ErrorCode.OK != code) {
            mView.showForgetPwdError(ErrorCodeManager.parseCode(code));
            return;
        }


        cancelTask();

        mView.showForgetPwdStarted();

//        Utils.queueTask(() -> {
//
//            int code1 = AccountManager.getInstance().setPwd(username, pwd);
//
//            if (ErrorCode.OK == code1) {
//                mView.showForgetPwdSuccess();
//            } else {
//                mView.showForgetPwdError(ErrorCodeManager.parseCode(code1));
//            }
//
//            mView.showForgetPwdCompleted();
//        });

        mTask = mHttpService.forget(username, pwd, new HttpRequestCallback() {
            @Override
            public void onSuccess(Object object) {

                int code1 = AccountManager.getInstance().setPwd(username, pwd);

                if (ErrorCode.OK == code1) {
                    mView.showForgetPwdSuccess();
                    mView.showForgetPwdCompleted();
                } else {
                    onError(code1, null);
                }

            }

            @Override
            public void onError(int code, String msg) {
                mView.showForgetPwdError(ErrorCodeManager.parseCode(code, msg));
                mView.showForgetPwdCompleted();
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        cancelTask();
        mView = null;
        mHttpService = null;
    }

    private void cancelTask() {
        if (null != mTask) mTask.cancel();
        mTask = null;
    }
}
