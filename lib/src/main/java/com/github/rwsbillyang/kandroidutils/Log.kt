package com.github.rwsbillyang.kandroidutils

import android.util.Log
import com.github.rwsbillyang.kandroidUtils.BuildConfig


/**
 * log配置，可以指定统一的log的Tag
 * */
object LogConfig{
    /**
     * 指定了统一的标签的话，同时在message前缀中添加classname
     * */
    var Tag: String? = null
        set(value)  {
            field = value
            if(!value.isNullOrBlank()) isShowClassName = true
        }

    /**
     * 输出log的类的名称是否出现在log中，默认为false
     * */
    var isShowClassName = false
}

/**
 * Any的扩展函数，任何类都可以输出log，当为DEBUG版本时输出log，否则不输出log
 * */
fun Any.log(message: String) {
    if (BuildConfig.DEBUG) Log.i(
        LogConfig.Tag ?: this::class.simpleName,
        if(LogConfig.isShowClassName) this::class.simpleName +"|" + message else message)
}

/**
 * Any的扩展函数，任何类都可以输出log，当为DEBUG版本时输出警告log，否则不输出log
 * */
fun Any.logw(message: String) {
    if (BuildConfig.DEBUG) Log.w(
        LogConfig.Tag ?: this::class.simpleName,
        if(LogConfig.isShowClassName) this::class.simpleName +"|" + message else message)
}
/**
 * Any的扩展函数，任何类都可以输出Throwable，当为DEBUG版本时输出警告log，否则不输出log
 * */
fun Any.log(error: Throwable) {
    if (BuildConfig.DEBUG){
        val msg = error.message ?: "Error"
        Log.e(
            LogConfig.Tag ?: this::class.simpleName,
            if(LogConfig.isShowClassName) this::class.simpleName +"|" + msg else msg)
    }
}

/**
 * Any的扩展函数，任何类都可以输出message和Throwable，当为DEBUG版本时输出警告log，否则不输出log
 * */
fun Any.log(message: String, error: Throwable) {
    if (BuildConfig.DEBUG) Log.e(
        LogConfig.Tag ?: this::class.simpleName,
        if(LogConfig.isShowClassName) this::class.simpleName +"|" + message else message
        , error)
}