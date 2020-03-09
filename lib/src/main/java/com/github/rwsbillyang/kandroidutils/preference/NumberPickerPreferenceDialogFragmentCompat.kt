package com.github.rwsbillyang.kandroidutils.preference

import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.preference.PreferenceDialogFragmentCompat
import com.github.rwsbillyang.kandroidUtils.R


class NumberPickerPreferenceDialogFragmentCompat: PreferenceDialogFragmentCompat() {

    private lateinit var numberPicker: NumberPicker
    private lateinit var numberPreference: NumberPickerPreference

    companion object {
        fun newInstance(key: String): NumberPickerPreferenceDialogFragmentCompat {
            val fragment =
                NumberPickerPreferenceDialogFragmentCompat()
            val args = Bundle()
            args.putString(ARG_KEY, key)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onBindDialogView(view: View?) {
        super.onBindDialogView(view)

        numberPicker = view?.findViewById(R.id.editNumberPicker) as NumberPicker
        numberPreference = preference as NumberPickerPreference

        numberPicker.apply {
            minValue = numberPreference.minValue
            maxValue = numberPreference.maxValue
            value = numberPreference.value
            wrapSelectorWheel = numberPreference.wrapSelectorWheel
        }

    }

    override fun onDialogClosed(p0: Boolean) {
        if (p0) {
            val newValue = numberPicker.value
            if (numberPreference.callChangeListener(newValue)) {
                numberPreference.apply {
                    value = newValue
                    summary = numberPreference.summary
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) dismiss()
    }
}