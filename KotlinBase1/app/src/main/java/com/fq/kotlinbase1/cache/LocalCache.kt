package com.fq.kotlinbase1.cache

/**
 * LocalCache
 * @author FaQiang on 2018/9/26 下午2:02
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
interface ICache {

    fun <T> put(key: String, value: T?): Boolean

    fun <T> get(key: String): T?

    fun <T> get(key: String, default: T): T

    fun contains(key: String): Boolean

    fun count(): Long

    fun delete(key: String): Boolean

    fun clear(): Boolean
}

object LocalCacheUtil : ICache {

    override fun <T> put(key: String, value: T?): Boolean {
        return true
    }

    override fun <T> get(key: String): T? {
        return null
    }

    override fun <T> get(key: String, default: T): T {
        return default
    }

    override fun contains(key: String): Boolean {
        return false
    }

    override fun count(): Long {
        return 0
    }

    override fun delete(key: String): Boolean {
        return true
    }

    override fun clear(): Boolean {
        return true
    }

}
