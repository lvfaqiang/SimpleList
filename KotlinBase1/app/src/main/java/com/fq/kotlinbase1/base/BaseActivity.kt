package com.fq.kotlinbase1.base

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.fq.kotlinbase1.utils.basic.ToastUtil
import com.fq.kotlinbase1.widget.ILoading
import com.fq.kotlinbase1.widget.LoadingView
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

//    protected val mFragmentUtil: FragmentUtil by lazy {
//        FragmentUtil(supportFragmentManager)
//    }

    private var mLoadingView: ILoading? = null

    /**
     *  横屏 or 竖屏  ， default 竖屏
     */
    protected fun isLandScape(): Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 禁止页面自动弹出输入法, or 禁止输入法顶起布局
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        // 设置页面始终竖屏展示
        requestedOrientation = if (isLandScape())
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mLoadingView = LoadingView(this)

        if (getLayoutId() != 0) {
            setContentView(getLayoutId())
        }
        isCreated = true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        if (useEventBus()) EventBus.getDefault().register(this)

        initUI(savedInstanceState)

        initListener()

        initData(savedInstanceState)

    }

    override fun onResume() {
        isStoped = false
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        isStoped = true
    }

    // -------------
    override fun showLoading() {
        mLoadingView?.let { loading ->
            loading.takeIf {
                !loading.isShowing() && isCreated && !isStoped
            }?.let {
                loading.show()
            }
        }
    }

    override fun disLoading() {
        mLoadingView?.let { loading ->
            loading.takeIf {
                loading.isShowing()
            }?.let {
                loading.dismiss()
            }
        }
    }

    override fun toastSuc(message: String) {
        if (isCreated && !isStoped) {
            ToastUtil.showToast(this, message)
        }
    }

    override fun toastSuc(strId: Int) {
        if (isCreated && !isStoped) {
            ToastUtil.showToast(this, getString(strId))
        }
    }

    override fun toastFailed(message: String) {
        if (isCreated && !isStoped) {
            ToastUtil.showToast(this, message)
        }
    }

    override fun toastFailed(strId: Int) {
        if (isCreated && !isStoped) {
            ToastUtil.showToast(this, getString(strId))
        }
    }

    override fun getContext(): Context? = this

    override fun getLifecycleOwner(): LifecycleOwner = this

// -------------

    abstract fun getLayoutId(): Int

    abstract fun initUI(savedInstanceState: Bundle?)

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initListener()


}