@file:JvmName("KotlinCode")

package com.fq.kotlin

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Runnable
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

/**
 * KotlinCode
 *
 */

fun doSomething() {

}

val channel = Channel<Runnable>(1)

abstract class Task : Runnable

abstract class UI : Runnable

suspend fun async(task: Task) {
    channel.send(task)
}

suspend fun ui(task: UI) {
    channel.send(task)
}

fun start() {
    launch {  }
}

object AndroidCommonPool:CoroutineDispatcher(){
    override fun dispatch(context: CoroutineContext, block: Runnable) {

    }

}
