package com.fq.kotlinbase1.base

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.lvfq.library.utils.FragmentUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import org.greenrobot.eventbus.EventBus

/**
 * BaseActivity
 * @author FaQiang on 2018/8/28 下午1:49
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
abstract class BaseActivity : AppCompatActivity(), IBase {

    var isCreated = false
        private set

    var isStoped = false
        private set

    protected val mPerissions: RxPermissions by lazy {
        RxPermissions(this)
    }

    protected val mFragmentUtil: FragmentUtil by lazy {
        FragmentUtil(supportFragmentManager)
    }

    override fun showLoading() {

    }

    override fun disLoading() {

    }


    override fun toastSuc(message: String) {

    }

    override fun toastFailed(message: String) {
    }

    override fun getLifecyclerOwner(): LifecycleOwner = this

    override fun getContext(): Context? = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 禁止页面自动弹出输入法, or 禁止输入法顶起布局
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        // 设置页面始终竖屏展示
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (getLayoutId() != 0) {
            setContentView(getLayoutId())
        }
        isCreated = true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        if (useEventbus()) EventBus.getDefault().register(this)

        initUI()
        initData()
        initListener()
    }


    override fun onStop() {
        super.onStop()
        isStoped = true
    }

    override fun onDestroy() {
        if (useEventbus()) EventBus.getDefault().unregister(this)
        disLoading()
        super.onDestroy()
    }


    abstract fun getLayoutId(): Int

    abstract fun initUI()
    abstract fun initData()
    abstract fun initListener()


}