package com.fq.kotlinbase1

import android.arch.lifecycle.Lifecycle
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fq.kotlinbase1.ext.scheduler.applyScheduler
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .bindUntilEvent(this, Lifecycle.Event.ON_STOP)
                .applyScheduler()

    }
}
