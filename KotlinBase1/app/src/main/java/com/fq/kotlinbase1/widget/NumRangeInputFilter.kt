package com.fq.kotlinbase1.widget

import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import android.util.Log

/**
 * EditText 数字输入范围过滤 (0 <= ? < mMaxValue && 小数点后保留 sPointerLength 位)
 * Created by stephen on 2018/1/7.
 */

class NumRangeInputFilter : InputFilter {
    // 小数点后的位数
    private var sPointerLength = 2

    constructor() {

    }

    constructor(maxValue: Int, pointerLength: Int) {
        mMaxValue = maxValue
        sPointerLength = pointerLength
    }

    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence {
        val sourceText = source.toString()
        val destText = dest.toString()

        // 新输入的字符串为空（删除剪切等）
        if (TextUtils.isEmpty(sourceText)) {
            return ""
        }

        // 拼成字符串
        val temp = (destText.substring(0, dstart)
                + sourceText.substring(start, end)
                + destText.substring(dend, dest.length))
        Log.v("temp", "-$temp")

        // 纯数字加小数点
        if (!temp.matches(REGEX.toRegex())) {
            Log.d("TAG", "!纯数字加小数点")
            return dest.subSequence(dstart, dend)
        }

        // 小数点的情况
        if (temp.contains(POINTER)) {
            // 第一位就是小数点
            if (temp.startsWith(POINTER)) {
                Log.d("TAG", "第一位就是小数点")
                return dest.subSequence(dstart, dend)
            }
            // 不止一个小数点
            if (temp.indexOf(POINTER) != temp.lastIndexOf(POINTER)) {
                Log.d("TAG", "不止一个小数点")
                return dest.subSequence(dstart, dend)
            }
        }

        val sumText = java.lang.Double.parseDouble(temp)
        //        if (sumText >= mMaxValue) {
        //            // 超出最大值
        //            Log.d("TAG", "超出最大值");
        ////            return dest.subSequence(dstart, dend);
        //            return mMaxValue + "";
        //        }
        // 有小数点的情况下
        if (temp.contains(POINTER)) {
            //验证小数点精度，保证小数点后只能输入两位
            if (!temp.endsWith(POINTER) && temp.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].length > sPointerLength) {
                Log.d("TAG", "保证小数点后只能输入两位")
                return dest.subSequence(dstart, dend)
            }
        } else if (temp.startsWith(POINTER) || temp.startsWith(ZERO_ZERO)) {
            // 首位只能有一个0
            Log.d("TAG", "首位只能有一个0")
            return dest.subSequence(dstart, dend)
        }

        Log.d("TAG", "正常情况")
        return source
    }

    companion object {

        // 只允许输入数字和小数点
        private val REGEX = "([0-9]|\\.)*"
        // 输入的最大金额
        private var mMaxValue = Integer.MAX_VALUE

        private val POINTER = "."

        private val ZERO_ZERO = "00"
    }
}