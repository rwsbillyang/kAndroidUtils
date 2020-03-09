package com.github.rwsbillyang.kandroidutils.preference

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.preference.DialogPreference
import com.github.rwsbillyang.kandroidUtils.R


/**
 * 提供滚动选值得preference，可以指定最大值和最小值
 * */
class NumberPickerPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : DialogPreference(context, attrs, defStyleAttr){
    companion object{
        const val DEFAULT_VALUE = 1
    }


    var minValue =
        DEFAULT_VALUE
    var maxValue = 100
    var value =
        DEFAULT_VALUE
        set(value) {
            field = value
            persistInt(value)
        }
    var wrapSelectorWheel = true


    //https://code.luasoftware.com/tutorials/android/override-layout-of-android-preference/
    //https://stackoverflow.com/questions/5533078/timepicker-in-preferencescreen
    //https://github.com/iyonaga/NumberPickerPreference/blob/master/app/src/main/java/com/example/iyonaga/numberpickerpreference/NumberPickerPreference.kt
    init {
        isPersistent = true
        //dialogIcon = null
        dialogLayoutResource = R.layout.pref_dialog_number
       // layoutResource = R.layout.preference

        setPositiveButtonText(android.R.string.ok)
        setNegativeButtonText(android.R.string.cancel)

        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.NumberPickerPreference)
        minValue = typedArray.getInt(R.styleable.NumberPickerPreference_minValue, minValue)
        maxValue = typedArray.getInt(R.styleable.NumberPickerPreference_maxValue, maxValue)
        wrapSelectorWheel = typedArray.getBoolean(R.styleable.NumberPickerPreference_wrapSelectorWheel, wrapSelectorWheel)
        typedArray.recycle()

    }

//    override fun onDialogClosed(positiveResult: Boolean) {
//        // When the user selects "OK", persist the new value
//        if (positiveResult) {
//            persistInt(value)
//        }
//    }

    override fun onSetInitialValue(restorePersistedValue: Boolean, defaultValue: Any?) {
        value = if (restorePersistedValue) {
            getPersistedInt(value)
        } else {
            if(defaultValue == null) DEFAULT_VALUE
            else defaultValue as Int
        }
    }

    override fun onGetDefaultValue(a: TypedArray?, index: Int): Any {
        return a!!.getInteger(index,
            DEFAULT_VALUE
        )
    }

    override fun getSummary(): CharSequence {
        return  getPersistedInt(value).toString()
    }


}