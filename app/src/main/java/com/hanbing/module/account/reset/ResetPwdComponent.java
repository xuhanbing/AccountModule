package com.hanbing.module.account.reset;

import dagger.Component;

/**
 * Created by hanbing on 2017/3/8
 */

@Component(modules = ResetPwdPresenterModule.class)
public interface ResetPwdComponent {
    void inject(ResetPwdActivity activity);
}
