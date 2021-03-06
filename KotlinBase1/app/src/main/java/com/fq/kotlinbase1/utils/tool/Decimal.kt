package com.fq.kotlinbase1.utils.tool

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Decimal
 * @author FaQiang on 2018/8/4 上午9:45
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
object Decimal {

    fun add(v1: String?, v2: String?): String {
        val result = checkValue(v1, v2)
        return result[0].add(result[1]).toPlainString()
    }

    fun sub(v1: String?, v2: String?): String {
        val result = checkValue(v1, v2)
        return result[0].subtract(result[1]).toPlainString()
    }

    fun mul(v1: String?, v2: String?): String {
        val result = checkValue(v1, v2)
        return result[0].multiply(result[1]).toPlainString()
    }

    fun div(v1: String?, v2: String?, scale: Int = 10): String {
        val result = checkValue(v1, v2)
        if (result[0] == BigDecimal("0") || result[1] == BigDecimal("0")) {
            return "0"
        } else {
            return result[0].divide(result[1], scale, RoundingMode.DOWN).toPlainString()
        }
    }

    // 以 value1 为参照，大于0 则表示 value1 大， 小于0 则 value1 小，等于0 则两个数相等。
    fun compareTo(value1: String?, value2: String?): Int {
        val result = checkValue(value1, value2)
        return result[0].compareTo(result[1])
    }

    private val checkValue = { v1: String?, v2: String? ->
        val value1 = if (!v1.isNullOrEmpty()) v1!! else "0"
        val value2 = if (!v2.isNullOrEmpty()) v2!! else "0"
        arrayOf(BigDecimal(value1), BigDecimal(value2))
    }
}


