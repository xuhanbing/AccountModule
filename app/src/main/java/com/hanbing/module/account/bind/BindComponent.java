package com.hanbing.module.account.bind;

import dagger.Component;

/**
 * Created by hanbing on 2017/3/21
 */

@Component(modules = BindPresenterModule.class)
public interface BindComponent {
    void inject(BindActivity activity);
}
