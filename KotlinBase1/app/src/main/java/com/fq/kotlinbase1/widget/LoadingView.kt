package com.fq.kotlinbase1.widget

import android.app.ProgressDialog
import android.content.Context

/**
 * LoadingView
 * @author FaQiang on 2018/9/26 下午6:05
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */

interface ILoading {
    fun show()
    fun dismiss()

    fun isShowing(): Boolean

    fun setMessage(message: String)

    fun setCancelable(boolean: Boolean)

    fun setCanceledOnTouchOutside(boolean: Boolean)
}


class LoadingView constructor(context: Context) : ILoading {

    private val dialog: ProgressDialog = ProgressDialog(context)

    override fun show() {
        dialog.show()
    }

    override fun dismiss() {
        dialog.dismiss()
    }

    override fun isShowing(): Boolean {
        return dialog.isShowing
    }

    override fun setMessage(message: String) {
        dialog.setMessage(message)
    }

    override fun setCancelable(boolean: Boolean) {
        dialog.setCancelable(boolean)
    }

    override fun setCanceledOnTouchOutside(boolean: Boolean) {
        dialog.setCanceledOnTouchOutside(boolean)
    }

}