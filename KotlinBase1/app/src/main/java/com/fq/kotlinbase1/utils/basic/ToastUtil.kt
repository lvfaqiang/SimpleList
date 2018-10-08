package com.fq.kotlinbase1.utils.basic

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.Toast


/**
 * ToastUtil
 * @author FaQiang on 2018/9/26 下午5:52
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
object ToastUtil {
    /**
     * Toast提示框
     *
     * @param context
     * @param message
     */
    private var toast: Toast? = null

    fun getToast(): Toast? {
        return toast
    }


    private val DURATION_DEF = Toast.LENGTH_SHORT

    /**
     * 取消当前提示
     */
    fun cancel() {
        toast?.cancel()
    }

    /**
     * 显示系统 Toast
     *
     * @param message 显示信息
     * @param gravity 显示位置
     */
    fun showToast(context: Context?, message: String, gravity: Int = 0, duration: Int = DURATION_DEF) {
        if (context != null) {
            Handler(Looper.getMainLooper()).post {
                if (toast == null) {
                    toast = Toast.makeText(context, message, duration)
                } else {
                    toast?.setText(message)
                }
                if (gravity != 0) {
                    toast?.setGravity(gravity, 0, 0)
                }
                toast?.show()
            }
        }
    }


    /**
     * 显示自定义 Toast
     *
     * @param view    自定义视图
     * @param gravity 显示位置
     */
    fun showToast(context: Context?, view: View, gravity: Int = 0, duration: Int = DURATION_DEF) {
        if (context != null) {
            Handler(Looper.getMainLooper()).post(Runnable {
                if (toast == null) {
                    toast = Toast(context)
                    toast?.view = view
                    toast?.duration = duration
                } else {
                    toast?.view = view
                }
                if (gravity == 0) {
                    toast?.setGravity(Gravity.CENTER, 0, 0)
                } else {
                    toast?.setGravity(gravity, 0, 0)
                }
                toast?.show()
            })
        }
    }

    /**
     * 显示自定义 Toast
     *
     * @param view    自定义视图
     * @param gravity 显示位置
     */
    fun showToast(context: Context?, layoutId: Int, gravity: Int = 0, duration: Int = DURATION_DEF) {
        if (context != null) {
            val view = View.inflate(context, layoutId, null)
            Handler(Looper.getMainLooper()).post(Runnable {
                if (toast == null) {
                    toast = Toast(context)
                    toast?.view = view
                    toast?.duration = duration
                } else {
                    toast?.view = view
                }
                if (gravity == 0) {
                    toast?.setGravity(Gravity.CENTER, 0, 0)
                } else {
                    toast?.setGravity(gravity, 0, 0)
                }
                toast?.show()
            })
        }
    }


}