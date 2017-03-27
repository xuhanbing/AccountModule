package com.hanbing.module.account;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Account type
 * Created by hanbing on 2017/3/20
 */
public enum AccountType {
    USERNAME,//general username
    MOBILE, //mobile
    QQ, //qq
    WEIXIN, //wechat
    SINA, //weibo
    OTHRER, //other
    ;

    public SHARE_MEDIA toShareMedia() {
        com.umeng.socialize.bean.SHARE_MEDIA shareMedia = null;
        switch (this) {
            case QQ:
                shareMedia = com.umeng.socialize.bean.SHARE_MEDIA.QQ;
                break;
            case WEIXIN:
                shareMedia = com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN;
                break;
            case SINA:
                shareMedia = com.umeng.socialize.bean.SHARE_MEDIA.SINA;
                break;
        }


        return shareMedia;
    }
}
