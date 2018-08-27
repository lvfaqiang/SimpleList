package com.fq.kotlin

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * 协程
 * @author lvfq
 * @date 2018/8/5 下午11:48
 * @mainFunction :
 *
 *  runBlocking  用于执行协程任务，通常只用于启动最外层协程
 *
 *  launch -> Job 用于执行协程任务
 *
 *  async/await -> Deferred<T>  这里 async 和 await 一般是成对使用的，用于执行协程任务，并得到执行结果。
 *
 */
fun main(args: Array<String>) = runBlocking {


    val job = launch {
        repeat(1000) {
            println("挂起中。。。$it , thread Name = ${Thread.currentThread().name}")
            delay(500L)
        }

    }

    val job2 = async {
        delay(300)
        return@async "hello"
    }

    println("job2 的输出${job2.await()}")

    delay(1300L)

    println(" thread : ${Thread.currentThread().name}")

    if (job.isActive) {
        println("job ------------")
        job.cancel()
    }
    job.join()

    println("主线程 is  end")

}