package com.fq.kotlinbase1

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Process
import android.support.multidex.MultiDexApplication
import java.util.*

/**
 * App
 * @author FaQiang on 2018/8/28 上午11:29
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class App : MultiDexApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
            private set

        fun exit() {
            AppLifecycle.finishAll()
            android.os.Process.killProcess(Process.myPid())
        }
    }


    override fun onCreate() {
        super.onCreate()
        mContext = this

        registerActivityLifecycleCallbacks(AppLifecycle)


    }

}


object AppLifecycle : Application.ActivityLifecycleCallbacks {
    private val activitys = LinkedList<Activity>()

    private fun add(activity: Activity?) = activity?.let { activitys.add(it) }

    private fun remove(activity: Activity?) = activity?.let { activitys.remove(it) }

    fun finishAll() {

        activitys.filter { !it.isFinishing }
                .forEach {
                    it.finish()
                }
    }

    fun latestActivity() = takeIf { activitys.size > 0 }?.let { activitys.last }


    override fun onActivityPaused(activity: Activity?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResumed(activity: Activity?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStarted(activity: Activity?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityDestroyed(activity: Activity?) {
        remove(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStopped(activity: Activity?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        add(activity)
    }

}

