package io.github.nevergobad.dependencyinjection;

import android.app.Application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.nevergobad.services.RecipeEndpoints;
import io.github.nevergobad.services.UserAgentInterceptor;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by andre on 05/06/16.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        final int CACHE_SIZE = 10 * 1_024 * 1_024;
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor loggingInterceptor,
                                     UserAgentInterceptor userAgentInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(userAgentInterceptor)
                .build();
    }

    @Provides
    UserAgentInterceptor providesUserAgentInterceptor(@Named(StaticConfigModule.USER_AGENT) String userAgent) {
        return new UserAgentInterceptor(userAgent);
    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    @Provides
    CallAdapter.Factory providesCallAdapterFactory() {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    HttpLoggingInterceptor providesHttpLogging(@Named(StaticConfigModule.DEBUG) boolean debugConfig) {
        HttpLoggingInterceptor.Level logLevel = debugConfig ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(logLevel);
        return httpLoggingInterceptor;
    }

    @Provides
    Retrofit.Builder provideRetrofit(OkHttpClient client, ObjectMapper mapper,
                                     CallAdapter.Factory callAdapter) {
        JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory.create(mapper);
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(jacksonConverterFactory);
    }

    @Provides
    @Singleton
    RecipeEndpoints provideRecipeEndpoints(Retrofit.Builder retrofitBuilder,
                                           @Named(StaticConfigModule.RECIPE_BASE_URL) String recipeBaseUrl) {
        return retrofitBuilder
                .baseUrl(recipeBaseUrl)
                .build()
                .create(RecipeEndpoints.class);
    }

}
