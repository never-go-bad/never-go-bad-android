package io.github.nevergobad.settings;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import io.github.nevergobad.R;

/**
 * Created by aoriani on 4/27/16.
 */
public class TimePickerPreferenceDialogFragment extends PreferenceDialogFragmentCompat {

    private static final String SAVED_STATE_HOUR = "TimePickerPreferenceDialogFragment.hour";
    private static final String SAVED_STATE_MINUTE = "TimePickerPreferenceDialogFragment.minute";

    private int mInitialHour;
    private int mInitialMinute;
    private TimePicker mTimePicker;

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

    private int getHour() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mTimePicker.getHour();
        } else {
            return mTimePicker.getCurrentHour();
        }
    }

    private int getMinute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mTimePicker.getMinute();
        } else {
            return mTimePicker.getCurrentMinute();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mInitialHour = this.getTimePreference().getHour();
            mInitialMinute = this.getTimePreference().getMinute();
        } else {
            mInitialHour = savedInstanceState.getInt(SAVED_STATE_HOUR);
            mInitialMinute = savedInstanceState.getInt(SAVED_STATE_MINUTE);
        }
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_STATE_HOUR, getHour());
        outState.putInt(SAVED_STATE_MINUTE, getMinute());
    }

    protected void onBindDialogView(View view) {
        mTimePicker = (TimePicker) view.findViewById(R.id.time_picker);
        mTimePicker.setIs24HourView(DateFormat.is24HourFormat(getContext()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(mInitialHour);
            mTimePicker.setMinute(mInitialMinute);
        } else {
            mTimePicker.setCurrentHour(mInitialHour);
            mTimePicker.setCurrentMinute(mInitialMinute);
        }

    }

    @Override
    public void onDialogClosed(boolean positiveButton) {
        if (positiveButton) {
            final TimePreference timePreference = getTimePreference();
            final int hour = getHour();
            final int minute = getMinute();
            if (timePreference.callChangeListener(hour, minute)) {
                timePreference.setTime(hour, minute);
            }
        }
    }
}
