package com.fq.kotlinbase1.ext.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable

/**
 * Activity
 * @author FaQiang on 2018/8/29 下午4:15
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *  reified 真泛型必须配合 inline 关键字使用，
 *  crossinline 表明 如果在 lambda 表达式中 进行 return ，不会影响 lambda 之外的后续代码执行。
 */

inline fun <reified T : Activity> Fragment.startActivity(requestCode: Int = -1, crossinline action: Intent.() -> Unit = {}) {
    startActivityForResult(
            Intent(context, T::class.java)
                    .apply {
                        action.invoke(this)
                    }
            , requestCode)
}


inline fun <reified T : Fragment> newFragment(vararg keyValues: Pair<String, Any?>): T {
    val newInstance = T::class.java.newInstance()
    val args = putArgs(*keyValues)
    newInstance.arguments = args
    return newInstance
}

fun putArgs(vararg keyValues: Pair<String, Any?>): Bundle {
    return Bundle().apply {
        keyValues.forEach {
            val value = it.second
            when (value) {
                is Int -> {
                    putInt(it.first, value)
                }
                is Long -> {
                    putLong(it.first, value)
                }
                is Float -> {
                    putFloat(it.first, value)
                }
                is Double -> {
                    putDouble(it.first, value)
                }
                is String -> {
                    putString(it.first, value)
                }
                is Boolean -> {
                    putBoolean(it.first, value)
                }
                is Serializable -> {
                    putSerializable(it.first, value)
                }
                is Parcelable -> {
                    putParcelable(it.first, value)
                }
            }
        }
    }
}


fun Fragment.startAction(intentAction: String = "", requestCode: Int = -1, action: Intent.() -> Unit = {}) {
    startActivityForResult(
            (takeIf { !intentAction.isEmpty() }?.let { return@let Intent(intentAction) }
                    ?: Intent())
                    .apply {
                        action.invoke(this)
                    }
            , requestCode)
}
