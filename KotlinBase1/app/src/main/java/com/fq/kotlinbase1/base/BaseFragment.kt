package com.fq.kotlinbase1.base

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fq.kotlinbase1.utils.basic.FragmentUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import org.greenrobot.eventbus.EventBus

/**
 * BaseFragment
 * @author FaQiang on 2018/8/28 下午3:40
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
abstract class BaseFragment : Fragment(), IBase {

    protected val mPerissions: RxPermissions by lazy {
        RxPermissions(this)
    }

    protected val mFragmentUtil: FragmentUtil by lazy {
        FragmentUtil(childFragmentManager)
    }

    var isViewCreated = false
        private set

    var isStoped = false
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutId() != 0) {
            return inflater.inflate(getLayoutId(), container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        isViewCreated = true

        initUI(view, savedInstanceState)
        initListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

    override fun onDestroyView() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroyView()
    }

    override fun showLoading() {

    }

    override fun disLoading() {

    }

    override fun toastSuc(message: String) {

    }

    override fun toastFailed(message: String) {

    }

    override fun getLifecycleOwner(): LifecycleOwner = this


    override fun getContext(): Context? = super.getContext()


    abstract fun getLayoutId(): Int

    abstract fun initUI(view: View, savedInstanceState: Bundle?)

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initListener()


}