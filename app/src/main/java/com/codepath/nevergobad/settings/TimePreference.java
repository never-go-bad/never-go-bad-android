package com.codepath.nevergobad.settings;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.widget.TextView;

import com.codepath.nevergobad.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by aoriani on 4/26/16.
 */
public class TimePreference extends DialogPreference {

    private static final String TIME_SEPARATOR = ":";
    private static final String DEFAULT_DEFAULT_TIME = "19:00"; // 7PM Duh!

    private TimeStruct mTime = TimeStruct.deserialize(DEFAULT_DEFAULT_TIME);
    private TextView mTimeWidget;

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
        setDialogLayoutResource(R.layout.time_preference_dialog);
        setWidgetLayoutResource(R.layout.time_preference_widget);
        setDialogTitle(R.string.settings_section_notification_time_dialog_title);
        setPositiveButtonText(R.string.set);
        setDialogIcon(null);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue) {
            mTime = TimeStruct.deserialize(getPersistedString(DEFAULT_DEFAULT_TIME));
        } else {
            mTime = TimeStruct.deserialize((String) defaultValue);
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        mTimeWidget = (TextView) holder.findViewById(R.id.time);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, mTime.hour);
        calendar.set(Calendar.MINUTE, mTime.minute);
        mTimeWidget.setText(SimpleDateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()));
    }

    public int getHour() {
        return mTime.hour;
    }

    public int getMinute() {
        return mTime.minute;
    }

    public void setTime(@IntRange(from = 0, to = 23) int hour, @IntRange(from = 0, to = 59) int minute) {
        boolean changed = (hour != mTime.hour) || (minute != mTime.minute);
        if (changed) {
            mTime.set(hour, minute);
            persistString(mTime.serialize());
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public boolean callChangeListener(@IntRange(from = 0, to = 23) int hour, @IntRange(from = 0, to = 59) int minute) {
        return callChangeListener(new TimeStruct(hour, minute).serialize());
    }

    private static final class TimeStruct {
        int hour;
        int minute;

        TimeStruct() {
        }

        TimeStruct(@IntRange(from = 0, to = 23) int hour, @IntRange(from = 0, to = 59) int minute) {
            set(hour, minute);
        }

        static TimeStruct deserialize(@NonNull String timeString) {
            String[] components = timeString.split(TIME_SEPARATOR);
            if (components.length == 2) {
                try {
                    int hour = Integer.valueOf(components[0]);
                    int minute = Integer.valueOf(components[1]);
                    return new TimeStruct(hour, minute);

                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("string must be in the <h:m> format", e);
                }
            } else {
                throw new IllegalArgumentException("string must be in the <h:m> format");
            }
        }

        String serialize() {
            return "" + hour + TIME_SEPARATOR + minute;
        }

        void set(@IntRange(from = 0, to = 23) int hour, @IntRange(from = 0, to = 59) int minute) {
            this.hour = hour;
            this.minute = minute;
        }
    }
}
