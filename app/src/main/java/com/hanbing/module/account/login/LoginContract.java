package com.hanbing.module.account.login;

import com.hanbing.module.account.BasePresenter;
import com.hanbing.module.account.BaseView;
import com.hanbing.module.account.bean.IUser;

/**
 * Created by hanbing on 2017/3/3
 */

public class LoginContract {

    public interface View extends BaseView<Presenter> {


        String getUsername();

        String getPwd();


        void showLoginStarted();

        void showLoginError(String msg);

        void showLoginSuccess(IUser user);

        void showLoginCompleted();

    }


    public interface Presenter extends BasePresenter {
        void login();
    }
}
