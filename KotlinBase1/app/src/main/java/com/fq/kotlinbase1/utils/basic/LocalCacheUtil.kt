package com.fq.kotlinbase1.utils.basic

import com.orhanobut.hawk.Hawk

/**
 * LocalCacheUtil
 * @author FaQiang on 2018/9/26 下午1:56
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
object LocalCacheUtil {


    fun <T> put(key: String, value: T?): Boolean {
        return Hawk.put(key, value)
    }

    fun <T> get(key: String): T? {
        return Hawk.get(key)
    }

    fun <T> get(key: String, default: T): T {
        return Hawk.get(key, default)
    }

    fun contains(key: String): Boolean {
        return Hawk.contains(key)
    }

    fun count(): Long {
        return Hawk.count()
    }

    fun delete(key: String): Boolean {
        return Hawk.delete(key)
    }

    fun deleteAll(): Boolean {
        return Hawk.deleteAll()
    }

}