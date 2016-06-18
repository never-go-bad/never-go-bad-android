package io.github.nevergobad;

import android.app.Application;

import io.github.nevergobad.dependencyinjection.ComponentManager;
import io.github.nevergobad.dependencyinjection.NetworkComponent;

/**
 * Created by andre on 17/06/16.
 */

public class NeverGoBadApplication extends Application {

    private NetworkComponent mNetworkComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.start(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ComponentManager.destroy();
    }
}
