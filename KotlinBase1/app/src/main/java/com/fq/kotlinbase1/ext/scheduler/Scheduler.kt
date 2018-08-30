package com.fq.kotlinbase1.ext.scheduler

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * scheduler
 * @author FaQiang on 2018/8/28 上午10:27
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
fun <T> Observable<T>.applyScheduler(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.applyScheduler(owner: LifecycleOwner): Observable<T> {
    return this.applyScheduler()
            .bindToLifecycle(owner)
}

fun <T> Observable<T>.applyScheduler(owner: LifecycleOwner, event: Lifecycle.Event): Observable<T> {
    return this.applyScheduler()
            .bindUntilEvent(owner, event)
}


fun <T> Flowable<T>.applyScheduler(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.applyScheduler(owner: LifecycleOwner): Flowable<T> {
    return this.applyScheduler().bindToLifecycle<T>(owner)
}

fun <T> Flowable<T>.applyScheduler(owner: LifecycleOwner, event: Lifecycle.Event): Flowable<T> {
    return this.applyScheduler().bindUntilEvent(owner, event)
}
