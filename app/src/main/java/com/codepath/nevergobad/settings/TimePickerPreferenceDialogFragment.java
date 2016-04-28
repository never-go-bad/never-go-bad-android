package com.codepath.nevergobad.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;

/**
 * Created by aoriani on 4/27/16.
 */
public class TimePickerPreferenceDialogFragment extends PreferenceDialogFragmentCompat {

    private static final String SAVED_STATE_HOUR = "TimePickerPreferenceDialogFragment.hour";
    private static final String SAVED_STATE_MINUTE = "TimePickerPreferenceDialogFragment.minute";

    private int mHour;
    private int mMinute;

    public static TimePickerPreferenceDialogFragment newInstance(String key) {
        TimePickerPreferenceDialogFragment fragment = new TimePickerPreferenceDialogFragment();
        Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    private TimePreference getTimePreference() {
        return (TimePreference) getPreference();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mHour = this.getTimePreference().getHour();
            mMinute = this.getTimePreference().getMinute();
        } else {
            mHour = savedInstanceState.getInt(SAVED_STATE_HOUR);
            mMinute = savedInstanceState.getInt(SAVED_STATE_MINUTE);
        }
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_STATE_HOUR, mHour);
        outState.putInt(SAVED_STATE_MINUTE, mMinute);
    }


    protected void onBindDialogView(View view) {

    }

    @Override
    public void onDialogClosed(boolean b) {

    }
}
