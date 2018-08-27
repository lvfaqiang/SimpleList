package com.fq.kotlin

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * ChannelTest
 * @author lvfq
 * @date 2018/8/24 下午10:34
 * @mainFunction :
 *
 */
fun main(args: Array<String>) = runBlocking {

    val value = Channel<Int>()

    launch {
        println(value.receive())
        println(put().receive())
    }

    launch {
        //        put(value)
        value.send(1)
    }
    Unit
}

fun put() = produce(CommonPool) {
    var i = 0
    while (true) {
        send(i++)
    }
}

suspend fun get(channel: Channel<Int>) {
    while (true) {
        println(channel.receive())
    }
}

suspend fun put(channel: Channel<Int>) {
    var i = 0
    while (true) {
        channel.send(i++)
    }
}