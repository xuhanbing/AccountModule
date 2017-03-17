package com.hanbing.module.account.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.hanbing.module.account.BaseActivity;
import com.hanbing.module.account.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterByUsernameActivity extends BaseActivity implements RegisterContract.View {


    @Nullable @BindView(R.id.username_et)
    TextInputEditText mUsernameEt;
    @BindView(R.id.pwd_et)
    TextInputEditText mPwdEt;
    @BindView(R.id.pwd_confirm_et)
    TextInputEditText mPwdConfirmEt;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;


    RegisterContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_by_username_activity);
        ButterKnife.bind(this);

        mPresenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.register_btn)
    public void onClick() {

        mPresenter.registerByUsername();
    }

    @Override
    public String getUsername() {
        return mUsernameEt.getText().toString();
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
    public void showRegisterStarted() {
        showProgress("Register...");
    }

    @Override
    public void showRegisterSuccess() {
        showSnackbar("Register success.");
    }

    @Override
    public void showRegisterError(String msg) {
        showSnackbar(msg);
    }

    @Override
    public void showRegisterCompleted() {
        dismissProgress();
    }

    @Override
    public void goNext() {

    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
