package com.codepath.nevergobad.services;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by andre on 10/06/16.
 */

public class UserAgentInterceptor implements Interceptor {

    private final String mUserAgent;

    public UserAgentInterceptor(String userAgent) {
        mUserAgent = userAgent;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("User-Agent", mUserAgent);
        return chain.proceed(requestBuilder.build());
    }
}
