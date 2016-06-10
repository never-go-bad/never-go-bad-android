package com.codepath.nevergobad.services;

import com.codepath.nevergobad.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by andre on 10/06/16.
 */

class UserAgentInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("User-Agent", BuildConfig.APPLICATION_ID
                + "-" + BuildConfig.VERSION_NAME
                + "-" + BuildConfig.BUILD_TYPE);
        return chain.proceed(requestBuilder.build());
    }
}
