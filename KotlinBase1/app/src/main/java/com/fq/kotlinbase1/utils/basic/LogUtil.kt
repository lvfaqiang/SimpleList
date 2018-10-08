package com.fq.kotlinbase1.utils.basic

import android.util.Log
import com.fq.kotlinbase1.BuildConfig

/**
 * LogUtil
 * @author FaQiang on 2018/9/28 上午11:42
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
object LogUtil {

    private var TAG: String = "KotlinBase"
    private var logOff = BuildConfig.DEBUG

    /**
     * 开关
     */
    fun setLogEnable(flag: Boolean?) {
        logOff = flag ?: logOff
    }

    fun i(msg: String?) {
        i(TAG, msg)
    }

    fun i(tag: String, msg: String?) {
        if (logOff) {
            Log.i(tag, msg)
        }
    }

    fun e(msg: String?) {
        e(TAG, msg)
    }

    fun e(tag: String, msg: String?) {
        if (logOff) {
            Log.e(tag, msg)
        }
    }

    fun d(msg: String?) {
        d(TAG, msg)
    }

    fun d(tag: String, msg: String?) {
        if (logOff) {
            Log.d(tag, msg)
        }
    }

    fun v(msg: String) {
        v(TAG, msg)
    }

    fun v(tag: String, msg: String?) {
        if (logOff) {
            Log.v(tag, msg)
        }
    }

    fun w(tag: String, msg: String?) {
        if (logOff) {
            Log.w(tag, msg)
        }
    }

    fun w(msg: String?) {
        w(TAG, msg)
    }
}
