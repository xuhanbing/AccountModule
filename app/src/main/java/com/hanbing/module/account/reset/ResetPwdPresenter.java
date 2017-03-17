package com.hanbing.module.account.reset;

import android.text.TextUtils;

import com.hanbing.module.account.Utils;
import com.hanbing.module.account.base.AccountManager;
import com.hanbing.module.account.base.ErrorCode;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.base.HttpService;

import javax.inject.Inject;

/**
 * Created by hanbing on 2017/3/6
 */

public class ResetPwdPresenter implements ResetPwdContract.Presenter {

    ResetPwdContract.View mView;

    HttpService.Task mTask;

    @Inject
    public ResetPwdPresenter(ResetPwdContract.View view) {
        assert  null != mView;
        mView = view;
    }

    @Inject
    public void setListeners(){
        mView.setPresenter(this);
    }

    @Override
    public void reset() {
        String userId = mView.getUserId();
        String oldPwd = mView.getOldPwd();
        String newPwd = mView.getNewPwd();
        String newPwdConfirm = mView.getNewPwdConfirm();


        int code = ErrorCode.OK;
        if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(newPwdConfirm)) {
            code = ErrorCode.PWD_NO_EMPTY;
        } else if (!Utils.equals(newPwd, newPwdConfirm)) {
            code = ErrorCode.PWD_NOT_SAME;
        }

        if (ErrorCode.OK != code) {
            mView.showResetPwdError(ErrorCodeManager.parseCode(code));
            return;
        }


        mView.showResetPwdStarted();

        Utils.queueTask(()->{
            int ret = AccountManager.getInstance().resetPwd(userId, oldPwd, newPwd);

            if (ErrorCode.OK != ret) {
                mView.showResetPwdError(ErrorCodeManager.parseCode(ret));
            } else {
                mView.showResetPwdSuccess();
            }

            mView.showResetPwdCompleted();
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
