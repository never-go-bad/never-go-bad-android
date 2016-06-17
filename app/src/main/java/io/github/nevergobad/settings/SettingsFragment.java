package io.github.nevergobad.settings;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import io.github.nevergobad.R;

/**
 * Created by aoriani on 4/20/16.
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        //TODO: Breaking into preference files so we can have a Notification settings Activity
        addPreferencesFromResource(R.xml.settings_preferences);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (preference instanceof TimePreference) {
            TimePickerPreferenceDialogFragment dialogFragment = TimePickerPreferenceDialogFragment
                    .newInstance(preference.getKey());
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getFragmentManager(), "android.support.v7.preference.PreferenceFragment.DIALOG");
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }
}
