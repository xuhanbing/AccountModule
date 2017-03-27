package com.hanbing.module.account.bind;

import com.hanbing.module.account.AccountType;
import com.hanbing.module.account.base.ErrorCode;
import com.hanbing.module.account.bean.BindItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hanbing on 2017/3/20
 */

public class BindManager {

    static BindManager INSTANCE = new BindManager();

    public static BindManager getInstance() {
        return INSTANCE;
    }



    public static final String SPLIT = "##########";


    Map<String, String> mMap = new HashMap<>();

    private BindManager() {

    }

    private String generateKey(AccountType accountType, String uid) {
        return accountType.name() + SPLIT + uid;
    }


    private AccountType parseAccountType(String key) {
        return AccountType.valueOf(key.split(SPLIT)[0]);
    }

    private String parseUid(String key) {
        return key.split(SPLIT)[1];
    }

    public int bind(String userId, AccountType accountType, String uid) {
        String key = generateKey(accountType, uid);

        if (mMap.containsKey(key)) {
            return ErrorCode.BIND_ALREADY_USED;
        } else {
            mMap.put(key, userId);
            return ErrorCode.OK;
        }
    }

    public int unbind(String userId, AccountType accountType, String uid) {
        String key = generateKey(accountType, uid);

        if (mMap.containsKey(key)) {


            Iterator<Map.Entry<String, String>> iterator = mMap.entrySet().iterator();


            int count = 0;
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                if (userId.equals(next.getValue())) {
                    count++;
                }
            }

            if (1 == count) {
                return ErrorCode.UNBIND_ONE_LEAST;
            }

            mMap.remove(key);
            return ErrorCode.OK;
        } else {
            return ErrorCode.UNBIND_NOT_BIND;
        }
    }


    List<BindItem> getBindList(String userId) {


        List<BindItem> list = new ArrayList<>();

        Map<AccountType, String> binds = new HashMap<>();
        Iterator<Map.Entry<String, String>> iterator = mMap.entrySet().iterator();


        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (userId.equals(next.getValue())) {

                String key = next.getKey();
                binds.put(parseAccountType(key), parseUid(key));
            }
        }

        for (int i = 0; i < mAccountTypes.length; i++) {

            BindItem bindItem = new BindItem();
            AccountType accountType = mAccountTypes[i];
            bindItem.accountType = accountType;


            if (binds.containsKey(accountType)) {
                bindItem.bind = true;
                bindItem.uid = binds.get(accountType);
            }

            list.add(bindItem);
        }



        return list;

    }

    public boolean isMobileBinded(String mobile) {
        Iterator<String> iterator = mMap.keySet().iterator();

        while (iterator.hasNext()) {
            if (iterator.next().contains(mobile)) {
                return true;
            }
        }

        return false;
    }

    AccountType[] mAccountTypes = new AccountType[] {
            AccountType.MOBILE,
            AccountType.QQ,
            AccountType.WEIXIN,
            AccountType.SINA
    };

}
