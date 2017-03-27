package com.hanbing.module.account.bind;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hanbing.module.account.AccountType;
import com.hanbing.module.account.BaseActivity;
import com.hanbing.module.account.R;
import com.hanbing.module.account.bean.BindItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindActivity extends BaseActivity implements BindContract.View {

    @BindView(R.id.id_et)
    EditText mIdEt;
    @BindView(R.id.go_btn)
    Button mGoBtn;
    @BindView(R.id.bind_list)
    ListView mBindList;


    BindAdapter mBindAdapter;

    @Inject
    BindPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        ButterKnife.bind(this);

        DaggerBindComponent.builder()
                .bindPresenterModule(new BindPresenterModule(this, this))
                .build()
                .inject(this);

    }

    @OnClick(R.id.go_btn)
    public void onClick() {
        mPresenter.getBindList();
    }


    @Override
    public String getUserId() {
        return mIdEt.getText().toString();
    }

    @Override
    public void showGetBindListStarted() {
        showProgress("Get bind list...");
    }

    @Override
    public void showGetBindListSuccess(List<BindItem> bindList) {
        showSnackbar("Get bind list success.");

        mBindList.setAdapter(mBindAdapter = new BindAdapter(bindList));
    }

    @Override
    public void showGetBindListError(String msg) {
        showSnackbar("Get bind list error : " + msg);

    }

    @Override
    public void showGetBindListCompleted() {
        dismissProgress();
    }

    @Override
    public void showBindStarted(AccountType accountType) {
        showProgress("Bind " + accountType + " ...");
    }

    @Override
    public void showBindSuccess(AccountType accountType, String uid) {
        showSnackbar("Bind " + accountType + " success.");

        if (null != mBindAdapter) mBindAdapter.updateBindState(accountType, uid, true);
    }

    @Override
    public void showBindError(AccountType accountType, String msg) {
        showSnackbar("Bind " + accountType + " error : " + msg);

    }

    @Override
    public void showBindCompleted(AccountType accountType) {
        dismissProgress();
    }

    @Override
    public void showUnbindStarted(AccountType accountType) {
        showProgress("Unbind " + accountType + " ...");

    }

    @Override
    public void showUnbindSuccess(AccountType accountType) {
        showSnackbar("Unbind " + accountType + " success.");
        if (null != mBindAdapter) mBindAdapter.updateBindState(accountType, null, false);
    }

    @Override
    public void showUnbindError(AccountType accountType, String msg) {
        showSnackbar("Unbind " + accountType + " error : " + msg);

    }

    @Override
    public void showUnbindCompleted(AccountType accountType) {
        dismissProgress();
    }

    @Override
    public void goBindMobileActivity() {
        Intent intent = BindMobileActivity.newIntent(this, null, true);
        startActivityForResult(intent, 1);
    }

    @Override
    public void goUnbindMobileActivity(String mobile) {
        Intent intent = BindMobileActivity.newIntent(this, mobile, false);
        startActivityForResult(intent, 1);
    }

    @Override
    public void setPresenter(BindContract.Presenter presenter) {
        mPresenter = (BindPresenter) presenter;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (1 == requestCode && RESULT_OK == resultCode) {
            String mobile = data.getStringExtra("mobile");
            boolean bind = data.getBooleanExtra("bind", false);

            mPresenter.bindOrUnbindMobile(mobile, bind);

        }
    }

    class BindAdapter extends BaseAdapter {

        List<BindItem> mBindItems;

        public BindAdapter(List<BindItem> bindItems) {
            mBindItems = bindItems;
        }

        @Override
        public int getCount() {
            return null != mBindItems ? mBindItems.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bind_list_item, parent, false);
            }


            BindItem bindItem = mBindItems.get(position);

            TextView title = (TextView) convertView.findViewById(R.id.title_tv);
            TextView value = (TextView) convertView.findViewById(R.id.value_tv);
            TextView btn = (TextView) convertView.findViewById(R.id.bind_btn);


            title.setText(bindItem.accountType.name());
            value.setText(bindItem.uid);
            btn.setText(bindItem.bind ? "Unbind" : "Bind");

            btn.setOnClickListener(v -> {
                mPresenter.bindOrUnbind(bindItem.accountType, bindItem.uid, !bindItem.bind);
            });


            return convertView;
        }


        public void updateBindState(AccountType accountType, String uid, boolean bind) {
            for (BindItem bindItem : mBindItems) {
                if (accountType == bindItem.accountType) {

                    bindItem.bind = bind;

                    if (bind) {
                        bindItem.uid = uid;
                    } else {
                        bindItem.uid = null;
                    }


                    notifyDataSetChanged();

                    break;
                }
            }
        }
    }
}
