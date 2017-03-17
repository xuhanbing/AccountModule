package com.hanbing.module.account.register;

import com.hanbing.module.account.BasePresenter;
import com.hanbing.module.account.BaseView;

/**
 * Created by hanbing on 2017/3/6
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {

        String getUsername();

        String getPwd();

        String getPwdConfirm();

        void showRegisterStarted();

        void showRegisterSuccess();

        void showRegisterError(String msg);

        void showRegisterCompleted();

        void goNext();
    }


    interface Presenter extends BasePresenter {
        void registerByUsername();

        void registerByMobile();
    }
}
