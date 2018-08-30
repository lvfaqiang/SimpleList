package com.fq.kotlinbase1.api

import android.view.View
import com.fq.kotlinbase1.base.IBase
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * BaseObserver
 * @author FaQiang on 2018/8/28 下午12:07
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
abstract class HxBaseObserver<T> : Observer<T> {

    protected var iBase: IBase?
    protected var errorMsg: String = ""
    protected var view: View? = null

    // -------------   构造函数     -----------
    constructor(iBase: IBase?, errorMsg: String = "", view: View?) {
        this.iBase = iBase
        this.errorMsg = errorMsg
        this.view = view
    }

    constructor(iBase: IBase?, errorMsg: String) : this(iBase, errorMsg, null)

    constructor(iBase: IBase?, view: View?) : this(iBase, "", view)

    constructor(iBase: IBase?) : this(iBase, null)

    constructor() : this(null)

    // ---------------  End         ----------


    override fun onSubscribe(d: Disposable) {
        showLoading()
        viewClick(false)
    }

    override fun onNext(t: T) {
        TODO()
    }


    override fun onError(e: Throwable) {
        disLoading()
        viewClick(true)
        onError(ApiException(e))
    }

    override fun onComplete() {
        disLoading()
        viewClick(true)
    }


    open fun onError(apiException: ApiException) {
        iBase?.let {
            it.toastFailed(
                    takeIf { errorMsg.isNotEmpty() }
                            ?.let { errorMsg }
                            ?: apiException.getMessage()
            )
        }
    }


    private fun showLoading() {
        iBase?.showLoading()
    }

    private fun disLoading() {
        iBase?.disLoading()
    }

    private fun viewClick(clickable: Boolean) {
        view?.isClickable = clickable
    }


}