package io.github.nevergobad.dependencyinjection;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Created by andre on 17/06/16.
 */

public class ComponentManager {

    private static ComponentManager sInstance;
    private NetworkComponent mNetworkComponent;

    public static void start(@NonNull Application application) {
        sInstance = new ComponentManager(application);
    }

    public static void destroy() {
        if (sInstance != null) {
            sInstance.internalDestroy();
            sInstance = null;
        }
    }

    private ComponentManager(@NonNull Application application) {
        mNetworkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
    }

    private void internalDestroy() {
        mNetworkComponent = null;
    }

    public @NonNull NetworkComponent get() {
        return mNetworkComponent;
    }
}
