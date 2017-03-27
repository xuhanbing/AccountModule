package com.hanbing.module.account.base;

/**
 * Created by hanbing on 2017/3/6
 */

public interface ErrorCode {


    int OK = 0;


    int USERNAME_NO_EMPTY = 1000;
    int USERNAME_EXIST = 1001;
    int USERNAME_NOT_EXIST = 1002;
    int USERNAME_INVALID = 1003;

    int PWD_NO_EMPTY = 1010;
    int PWD_ERROR = 1011;
    int PWD_SAME = 1012;
    int PWD_INVALID = 1013;
    int PWD_NOT_SAME = 1014;

    int USERNAME_OR_PWD_NO_EMPTY = 1101;
    int USERNAME_LENGTH_INVALID = 1102;

    int MOBILE_NO_EMPTY = 1103;
    int MOBILE_INVALID = 1104;
    int MOBILE_NOT_MATCH = 1105;

    int VERIFY_CODE_NO_EMPTY = 1106;
    int VERIFY_CODE_LENGTH_INVALID = 1107;
    int VERIFY_CODE_ERROR = 1108;

    int MOBILE_EXIST = 1109;
    int MOBILE_NOT_EXIST = 1110;

    int BIND_ALREADY_USED = 1201;
    int UNBIND_NOT_BIND = 1202;
    int UNBIND_ONE_LEAST = 1203;
}
