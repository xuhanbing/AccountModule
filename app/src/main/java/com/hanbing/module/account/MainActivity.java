package com.hanbing.module.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hanbing.module.account.base.AccountManager;
import com.hanbing.module.account.base.ErrorCodeManager;
import com.hanbing.module.account.base.HttpService;
import com.hanbing.module.account.bind.BindActivity;
import com.hanbing.module.account.verify.VerifyCodeActivity;
import com.hanbing.module.account.forget.ForgetPwdActivity;
import com.hanbing.module.account.login.LoginActivity;
import com.hanbing.module.account.register.RegisterByMobileActivity;
import com.hanbing.module.account.register.RegisterByUsernameActivity;
import com.hanbing.module.account.reset.ResetPwdActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;
    @BindView(R.id.forget_btn)
    Button mForgetBtn;
    @BindView(R.id.fast_login_btn)
    Button mFastLoginBtn;
    @BindView(R.id.third_login_btn)
    Button mThirdLoginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        ErrorCodeManager.getInstance().init(this);
        AccountManager.getInstance().register("15968879083", "123");
        HttpService.getInstance().init(this);


        View view1 = getWindow().getDecorView();
        View view2 = findViewById(R.id.activity_main);




    }


    @OnClick({R.id.login_btn, R.id.register_btn, R.id.forget_btn, R.id.fast_login_btn, R.id.third_login_btn, R.id.register_mobile_btn, R.id.reset_btn, R.id.bind_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                startActivity(new Intent(this, LoginActivity.class));
//            {
//
//                Activity activity = this;
//                UMImage umImage = new UMImage(activity, "https://dimg06.c-ctrip.com/images/fd/tg/g2/M06/85/D5/CghzgVWwo2iAYSBiAC4SDFV0_4Y093_C_210_118.jpg");
//
//                ShareContent shareContent = new ShareContent();
//                shareContent.mMedia = umImage;
//                ShareAction shareAction = new ShareAction(activity);
//                shareAction.setPlatform(SHARE_MEDIA.QQ);
//                shareAction.setShareContent(shareContent);
//                UMShareAPI.get(activity).doShare(activity, shareAction, new UMShareListener() {
//                    @Override
//                    public void onStart(SHARE_MEDIA share_media) {
//
//                    }
//
//                    @Override
//                    public void onResult(SHARE_MEDIA share_media) {
//
//                    }
//
//                    @Override
//                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA share_media) {
//
//                    }
//                });
//            }
            break;
            case R.id.register_btn:
                startActivity(new Intent(this, RegisterByUsernameActivity.class));
                break;
            case R.id.register_mobile_btn:
                startActivity(new Intent(this, RegisterByMobileActivity.class));
                break;
            case R.id.forget_btn:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.fast_login_btn:
                startActivity(new Intent(this, VerifyCodeActivity.class));
                break;
            case R.id.third_login_btn:
                break;

            case R.id.reset_btn:
                startActivity(new Intent(this, ResetPwdActivity.class));

                break;
            case R.id.bind_btn:
                startActivity(new Intent(this, BindActivity.class));

                break;
        }
    }
}
