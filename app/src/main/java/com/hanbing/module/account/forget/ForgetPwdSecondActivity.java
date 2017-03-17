package com.hanbing.module.account.forget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.hanbing.module.account.App;
import com.hanbing.module.account.BaseActivity;
import com.hanbing.module.account.R;
import com.hanbing.module.account.base.AppComponent;
import com.hanbing.module.account.base.AppModule;
import com.hanbing.module.account.base.DaggerAppComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPwdSecondActivity extends BaseActivity implements ForgetPwdContract.View {

    public static final String KEY_MOBILE = "mobile";
    @BindView(R.id.pwd_et)
    TextInputEditText mPwdEt;
    @BindView(R.id.pwd_confirm_et)
    TextInputEditText mPwdConfirmEt;
    @BindView(R.id.finish_btn)
    Button mFinishBtn;

    public static Intent newIntent(Context context, String mobile) {

        Intent intent = new Intent(context, ForgetPwdSecondActivity.class);

        intent.putExtra(KEY_MOBILE, mobile);
        return intent;
    }

    String mMobile;

//    ForgetPwdContract.Presenter mPresenter;

    @Inject
    ForgetPwdPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pwd_second_activity);
        ButterKnife.bind(this);

        mMobile = getIntent().getStringExtra(KEY_MOBILE);

//        mPresenter = new ForgetPwdPresenter(this);

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .build()
                .plus(new ForgetPwdPresenterModule(this))
                .inject(this);
    }


    @Override
    public String getUsername() {
        return mMobile;
    }

    @Override
    public String getPwd() {
        return mPwdEt.getText().toString();
    }

    @Override
    public String getPwdConfirm() {
        return mPwdConfirmEt.getText().toString();
    }

    @Override
    public void showForgetPwdStarted() {
        showProgress("Forget password...");
    }

    @Override
    public void showForgetPwdSuccess() {
        showSnackbar("Forget password success.");
    }

    @Override
    public void showForgetPwdError(String msg) {
        showSnackbar(msg);
    }

    @Override
    public void showForgetPwdCompleted() {
        dismissProgress();
    }

    @Override
    public void setPresenter(ForgetPwdContract.Presenter presenter) {
        mPresenter = (ForgetPwdPresenter) presenter;
    }

    @OnClick(R.id.finish_btn)
    public void onClick() {
        mPresenter.forget();
    }
}
