package com.fq.kotlinbase1.utils.basic

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import com.fq.kotlinbase1.R

/**
 * Dialog
 * @author FaQiang on 2018/9/27 上午10:09
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */


class DialogBuilder {
    private var isCancelable = true
    private var isCanceledOnTouchOutside = true
    private var mGravity: Int = -1

    private var mWidth = 0
    private var mHeight = 0

    private var mWidthScale = 0.86f
    private var mHeightScale = 0f

    fun setWidth(width: Int): DialogBuilder {
        mWidth = width
        return this
    }

    fun setWidth(scale: Float): DialogBuilder {
        mWidthScale = scale
        return this
    }

    fun setHeight(height: Int): DialogBuilder {
        mHeight = height
        return this
    }

    fun setHeight(scale: Float): DialogBuilder {
        mHeightScale = scale
        return this
    }

    fun setGravity(gravity: Int): DialogBuilder {
        mGravity = gravity
        return this
    }

    fun setCancelable(cancelable: Boolean): DialogBuilder {
        isCancelable = cancelable
        return this
    }

    fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): DialogBuilder {
        isCanceledOnTouchOutside = canceledOnTouchOutside
        return this
    }


    fun build(context: Context, view: View): Dialog {
        val dialog = buildDialog(context)
        dialog.setContentView(view)

        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)

        val window = dialog.window
        if (mGravity != -1) {
            window.setGravity(mGravity)
        }
        val display = (context as Activity).windowManager.defaultDisplay
        val windowAttr = window.attributes

        if (mWidthScale != 0f) {
            windowAttr.width = (display.width * mWidthScale).toInt()
        } else {
            if (mWidth != 0) {
                windowAttr.width = mWidth
            }
        }

        if (mHeightScale != 0f) {
            windowAttr.height = (display.height * mHeightScale).toInt()
        } else {
            if (mHeight != 0) {
                windowAttr.height = mHeight
            }
        }

        window.attributes = windowAttr

        dialog.show()

        return dialog
    }

    private fun buildDialog(context: Context): Dialog {
        return Dialog(context, R.style.style_loading_light_dialog)
    }
}