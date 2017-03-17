package com.hanbing.module.account.base;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by hanbing on 2017/3/6
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountScoped {
}
