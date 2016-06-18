package io.github.nevergobad.dependencyinjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andre on 17/06/16.
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }


    @Singleton
    @Provides
    Application provideApplicationModule() {
        return mApplication;
    }

}
