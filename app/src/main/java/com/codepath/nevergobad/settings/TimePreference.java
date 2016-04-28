package com.codepath.nevergobad.settings;


import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import com.codepath.nevergobad.R;

/**
 * Created by aoriani on 4/26/16.
 */
public class TimePreference extends DialogPreference {

    private int mHour;
    private int mMinute;

    public TimePreference(Context context) {
        super(context);
        init();
    }

    public TimePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        setDialogLayoutResource(R.layout.time_preference);
    }

    public int getHour() {
        return mHour;
    }

    public int getMinute() {
        return mMinute;
    }
}
