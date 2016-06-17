package io.github.nevergobad.models;

import android.support.annotation.NonNull;

/**
 * Created by andre on 11/06/16.
 */

public enum DietaryRestriction {

    Healthy("652"),
    Vegan("656"),
    LowCholesterol("655"),
    Kosher("645"), Raw("659"),
    KosherPesach("658");

    private final String mValue;

    DietaryRestriction(@NonNull String value) {
        mValue = value;
    }

    @NonNull
    public String value() {
        return mValue;
    }
}
