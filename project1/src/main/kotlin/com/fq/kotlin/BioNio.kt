package com.fq.kotlin

import kotlinx.coroutines.experimental.channels.Channel
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.ByteBuffer

/**
 * BioNio
 * @author lvfq
 * @date 2018/8/26 上午11:41
 * @mainFunction :
 *
 *  BIO : Blocking IO
 *  NIO  : Non-blocking IO
 *
 */


fun main(args: Array<String>) {

}

/**
 * BIO 实现
 */
fun copyFileBio(fileOld: File, fileNew: File) {
    FileInputStream(fileOld).use { input ->
        FileOutputStream(fileNew).use { output ->
            val buf = ByteArray(1024)
            while (true) {
                val byteRead = input.read(buf)
                if (byteRead <= 0) {
                    break
                }
                output.write(buf, 0, byteRead)
            }
        }
    }
}

/**
 * NIO 实现
 */
fun copyFileNio(fileOld: File, fileNew: File) {
    FileInputStream(fileOld).channel.use { input ->
        FileOutputStream(fileNew).channel.use { output ->
            //            val byteBuffer = ByteBuffer.allocate(1024)  // NIO 主要是因为这个 ByteBuffer  所以它是一个非阻塞态的一个模型
//            while (true) {
//                byteBuffer.clear()
//                if (input.read(byteBuffer) <= 0) {
//                    break
//                }
//                byteBuffer.flip()   // 移动指针至最新位置
//                output.read(byteBuffer)
//            }
            output.transferFrom(input, 0, input.size()) // JDK 内部实现，效果等同于 上面所注释部分代码
        }
    }
}