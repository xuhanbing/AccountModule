package com.hanbing.module.account.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by hanbing on 2016/10/21
 */

public class HttpService {

    private static final String TAG = "HttpService";

    /**
     * 请求任务
     */
    public static class Task {

        Disposable mSubscription;

        public Task() {

        }

        public Task(Disposable subscription) {
            mSubscription = subscription;
        }

        /**
         * 取消任务
         * 可能并不是真正中断或取消，只是不回调结果
         */
        public void cancel() {
            if (null != mSubscription && !mSubscription.isDisposed())
                mSubscription.dispose();
            mSubscription = null;

        }

        public static Task create(Observable observable, HttpRequestCallback callback) {
            return create(null, observable, callback);
        }

        public static Task create(Object tag, Observable observable, HttpRequestCallback callback) {
            return new Task(observable.subscribeOn(Schedulers.io())
                    .delay(500, TimeUnit.MILLISECONDS) //延迟500ms回调结果
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(o -> {
                        Log.e(TAG, "success:" + o);
                        callback.onSuccess(o);
                    }, throwable -> {
                        Log.e(TAG, "error:" + throwable.toString());
                        callback.onError(-1, throwable.toString());
                    }));
        }


    }


    static HttpService mInstance = new HttpService();

    public static HttpService getInstance() {
        return mInstance;
    }

    ApiService mApiService;
    Context mContext;
    boolean mIninited = false;

    private static ApiService getApiService() {
        return mInstance.mApiService;
    }

    public HttpService() {

        retrofit2.Retrofit build = new retrofit2.Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://www.baidu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(new MyConverter())
                .build();

        mApiService = build.create(ApiService.class);
    }

    public void init(Context context) {
        mContext = context;
        mIninited = true;
    }

    private void checkInit() {
        if (!mIninited)
            throw new IllegalStateException("You should initialize first.");
    }



    public Task getUser(String name, HttpRequestCallback callback) {
        return Task.create(mApiService.getUser(name), callback);
    }



    public Task login(String username, String pwd, HttpRequestCallback callback) {
        return Task.create(mApiService.login(username, pwd), callback);
    }

    public Task forget(String username, String pwd, HttpRequestCallback callback) {
        return Task.create(mApiService.forget(username, pwd), callback);
    }



    class MyConverter extends Converter.Factory {


        GsonConverterFactory mGsonConverterFactory = GsonConverterFactory.create(new Gson());
        ScalarsConverterFactory mScalarsConverterFactory = ScalarsConverterFactory.create();

        Gson mGson = new Gson();


        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

            Converter<ResponseBody, ?> responseBodyConverter = mScalarsConverterFactory.responseBodyConverter(type, annotations, retrofit);
            if (null != responseBodyConverter) {
                return responseBodyConverter;
            } else {
//                return mGsonConverterFactory.responseBodyConverter(type, annotations, retrofit);

                TypeAdapter<?> adapter = mGson.getAdapter(TypeToken.get(type));
                return new MyResponseBodyConverter<>(mGson, type, adapter);
            }
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return GsonConverterFactory.create().requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }



        class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {
            private final Gson gson;
            private final Type type;
            private final TypeAdapter<T> adapter;

            MyResponseBodyConverter(Gson gson, Type type, TypeAdapter<T> adapter) {
                this.gson = gson;
                this.type = type;
                this.adapter = adapter;
            }

            @Override
            public T convert(ResponseBody value) throws IOException {
                String result = value.string();

                Log.e(TAG, "result = " + result);

                try {

                    if (TextUtils.isEmpty(result)) {
                        //没有数据，抛出异常
                        throw new IOException("");
                    }

                    //如果是mingBase类型数据
                    Type stringType = new TypeToken<String>() {
                    }.getType();

                    if (type != stringType) {

                    }


                    return adapter.fromJson(result);
                } finally {
                    value.close();
                }
            }
        }
    }




}
