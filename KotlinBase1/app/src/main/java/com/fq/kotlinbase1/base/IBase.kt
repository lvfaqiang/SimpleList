package com.fq.kotlinbase1.base

import android.arch.lifecycle.LifecycleOwner
import android.content.Context

/**
 * IBase
 * @author FaQiang on 2018/8/28 上午9:11
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
interface IBase {

    // Loading...

    fun showLoading()

    fun disLoading()


    // EventBus
    fun useEventbus(): Boolean = false

    // Toast
    fun toastSuc(message: String)

    fun toastFailed(message: String)


    fun getLifecyclerOwner(): LifecycleOwner

    fun getContext(): Context?

}