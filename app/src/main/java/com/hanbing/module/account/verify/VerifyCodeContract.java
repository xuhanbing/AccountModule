package com.hanbing.module.account.verify;

import com.hanbing.module.account.BasePresenter;
import com.hanbing.module.account.BaseView;

/**
 * Created by hanbing on 2017/3/3
 */

public class VerifyCodeContract {

    public interface View extends BaseView<Presenter> {
        String getMobile();
        String getVerifyCode();

        void setRequestBtnEnabled(boolean enabled);

        long getCountDownMillis();
        void showCountDownMillis(long timeInMillis);

        void showRequestVerifyCodeStarted();
        void showRequestVerifyCodeSuccess(String verifyCode);
        void showRequestVerifyCodeError(String msg);
        void showRequestVerifyCodeCompleted();


        void showCheckVerifyCodeStarted();
        void showCheckVerifyCodeSuccess();
        void showCheckVerifyCodeError(String msg);
        void showCheckVerifyCodeCompleted();


        void goNext();

    }
    public interface Presenter extends BasePresenter {
        void requestVerifyCode(VerifyCodeType verifyCodeType);
        void checkVerifyCode();
    }
}
