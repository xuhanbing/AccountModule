package com.hanbing.module.account.base;

import com.hanbing.module.account.bean.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hanbing on 2016/10/21
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("http://192.168.1.10/php/2.php")
    Observable<String> getUser(
            @Field("name") String name
    );
    @FormUrlEncoded
    @POST("http://192.168.1.10/php/2.php")
    Observable<String> login(
            @Field("username") String username,
            @Field("password") String pwd
    );

    @FormUrlEncoded
    @POST("http://192.168.1.10/php/2.php")
    Observable<String> forget(
            @Field("username") String username,
            @Field("password") String pwd
    );
}
