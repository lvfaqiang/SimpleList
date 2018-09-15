package com.fq.kotlinbase1.utils

import android.view.View

/**
 * ViewClickUtil
 * @author FaQiang on 2018/9/11 下午6:18
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
object ViewClickUtil {

    fun getOnClickListener(view: View): View.OnClickListener? {
        val info = getListenerInfo(view)
        info?.let {
            val m = ReflectionUtil.getFieldValue(it, "mOnClickListener") as View.OnClickListener?
            return m
        }
        return null
    }

    fun removeOnClickListener(view: View) {
        val info = getListenerInfo(view)
        info?.let {
            ReflectionUtil.setFieldValue(it, "mOnClickListener", null)
        }
    }

    private fun getListenerInfo(view: View): Any? {
        val method = ReflectionUtil.getDeclaredMethod(view, "getListenerInfo")
        method?.isAccessible = true
        val info = method?.invoke(view)
        return info
    }

}
