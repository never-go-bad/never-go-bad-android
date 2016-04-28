package com.codepath.nevergobad.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.TextView;

import com.codepath.nevergobad.R;


/**
 * Created by aoriani on 4/23/16.
 */
public class NumberPickerPreference extends Preference {

    private static final int MIN_MIN_VALUE = 0;
    private static final int MAX_MAX_VALUE = 99;
    private static final int DEFAULT_VALUE = 0;

    @IntRange(from = MIN_MIN_VALUE, to = MAX_MAX_VALUE)
    private int mMinValue = MIN_MIN_VALUE;

    @IntRange(from = MIN_MIN_VALUE, to = 99)
    private int mMaxValue = MAX_MAX_VALUE;

    @IntRange(from = 0, to = 99)
    private int mCurrValue = DEFAULT_VALUE;

    private ImageButton mDecrementImageButton;
    private ImageButton mIncrementImageButton;
    private TextView mValueView;

    public NumberPickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public NumberPickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public NumberPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NumberPickerPreference(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        setWidgetLayoutResource(R.layout.numberpicker_preference);
        setSelectable(false);

        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberPickerPreference);
            try {
                setMinValue(a.getInteger(R.styleable.NumberPickerPreference_minValue, MIN_MIN_VALUE));
                setMaxValue(a.getInteger(R.styleable.NumberPickerPreference_maxValue, MAX_MAX_VALUE));
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        mDecrementImageButton = (ImageButton) holder.findViewById(R.id.bt_decrement);
        Drawable decrementIcon = mDecrementImageButton.getDrawable();
        if (decrementIcon != null) {
            DrawableCompat.setTintList(decrementIcon, getContext().getResources().getColorStateList(R.color.number_picker_button));
        }

        mDecrementImageButton.setOnClickListener(view -> {
            setValue(mCurrValue - 1);
            updateViews();
        });

        mIncrementImageButton = (ImageButton) holder.findViewById(R.id.bt_increment);
        Drawable incrementIcon = mIncrementImageButton.getDrawable();
        if (decrementIcon != null) {
            DrawableCompat.setTintList(incrementIcon, getContext().getResources().getColorStateList(R.color.number_picker_button));
        }

        mIncrementImageButton.setOnClickListener(view -> {
            setValue(mCurrValue + 1);
            updateViews();
        });

        mValueView = (TextView) holder.findViewById(R.id.number_value);

        updateViews();

    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        setValue(restorePersistedValue ? getPersistedInt(DEFAULT_VALUE) : (Integer) defaultValue);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getInteger(index, DEFAULT_VALUE);
    }

    public void setMinValue(@IntRange(from = MIN_MIN_VALUE, to = MAX_MAX_VALUE) int value) {
        if (MIN_MIN_VALUE <= value && value <= MAX_MAX_VALUE) {
            mMinValue = value;

            //Ensure consistent values
            if (mMinValue > mMaxValue) {
                mMaxValue = MAX_MAX_VALUE;
            }
        } else {
            throw new IllegalArgumentException("value is out of range");
        }
    }

    public void setMaxValue(@IntRange(from = MIN_MIN_VALUE, to = MAX_MAX_VALUE) int value) {
        if (MIN_MIN_VALUE <= value && value <= MAX_MAX_VALUE) {
            mMaxValue = value;

            //Ensure consistent values
            if (mMaxValue < mMinValue) {
                mMinValue = MIN_MIN_VALUE;
            }
        } else {
            throw new IllegalArgumentException("value is out of range");
        }
    }

    public void setValue(@IntRange(from = MIN_MIN_VALUE, to = MAX_MAX_VALUE) int value) {
        if (MIN_MIN_VALUE <= value && value <= MAX_MAX_VALUE) {
            mCurrValue = value;
            persistInt(mCurrValue);
        } else {
            throw new IllegalArgumentException("value is out of range");
        }
    }

    public int getValue() {
        return mCurrValue;
    }

    public int getMinValue() {
        return mMinValue;
    }

    public int getMaxValue() {
        return mMaxValue;
    }

    private void updateViews() {
        mDecrementImageButton.setEnabled(mCurrValue > mMinValue);
        mIncrementImageButton.setEnabled(mCurrValue < mMaxValue);
        mValueView.setText(String.valueOf(mCurrValue));
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        // Check whether this Preference is persistent (continually saved)
        if (isPersistent()) {
            // No need to save instance state since it's persistent,
            // use superclass state
            return superState;
        }

        // Create instance of custom BaseSavedState
        final SavedState myState = new SavedState(superState);
        // Set the state's value with the class member that holds current
        // setting value
        myState.value = mCurrValue;
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // Check whether we saved the state in onSaveInstanceState
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save the state, so call superclass
            super.onRestoreInstanceState(state);
            return;
        }

        // Cast state to custom BaseSavedState and pass to superclass
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());

        // Set this Preference's widget to reflect the restored state
        setValue(myState.value);
        updateViews();
    }

    private static class SavedState extends BaseSavedState {
        // Member that holds the setting's value
        // Change this data type to match the type saved by your Preference
        int value;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            // Get the current preference's value
            value = source.readInt();  // Change this to read the appropriate data type
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            // Write the preference's value
            dest.writeInt(value);  // Change this to write the appropriate data type
        }

        // Standard creator object using an instance of this class
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {

                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }



}
