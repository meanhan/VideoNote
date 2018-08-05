package com.xuhan.videonote.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author  xuhan on 17-11-1.
 */

public abstract class HttpCallBack implements Callback<String> {

    /**
     * 请求成功
     * @param s
     */
     abstract void onSuccess(String s);

    /**
     * 请求失败
     * @param t
     */
    public abstract void onFailed(Throwable t);

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        onFailed(t);
    }
}
