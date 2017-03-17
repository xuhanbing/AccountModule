package com.hanbing.module.account.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hanbing.module.account.R;

import butterknife.ButterKnife;

public class RegisterByMobileSecondActivity extends RegisterByUsernameActivity {


    public static final String KEY_MOBILE = "mobile";

    public static Intent newIntent(Context context, String mobile) {

        Intent intent = new Intent(context, RegisterByMobileSecondActivity.class);

        intent.putExtra(KEY_MOBILE, mobile);
        return intent;
    }

    String mMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_by_mobile_second_activity);
        ButterKnife.bind(this);

        mMobile = getIntent().getStringExtra(KEY_MOBILE);
    }

    @Override
    public String getUsername() {
        return mMobile;
    }

    @Override
    public void onClick() {
        mPresenter.registerByMobile();
    }
}
