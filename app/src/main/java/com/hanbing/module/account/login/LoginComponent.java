package com.hanbing.module.account.login;

import com.hanbing.module.account.base.AccountScoped;
import com.hanbing.module.account.base.AppComponent;
import com.hanbing.module.account.base.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by hanbing on 2017/3/6
 */
@AccountScoped
@Component(dependencies = {AppComponent.class}, modules = {LoginPresenterModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
    void inject(LoginFragment activity);

}
