package com.hanbing.module.account.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hanbing.module.account.BaseFragment;
import com.hanbing.module.account.R;
import com.hanbing.module.account.base.HttpService;
import com.hanbing.module.account.bean.IUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View {


    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.login_btn)
    Button mLoginBtn;

    LoginContract.Presenter mPresenter;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);


        ButterKnife.bind(this, view);


        return view;
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
    public void showLoginStarted() {
        showProgress("Login");
    }

    @Override
    public void showLoginError(String msg) {
        showSnackbar(msg);
    }

    @Override
    public void showLoginSuccess(IUser user) {
        showSnackbar("Login success.");
    }

    @Override
    public void showLoginCompleted() {
        dismissProgress();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                mPresenter.login();
                break;
        }
    }

}
