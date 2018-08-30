package com.fq.kotlinbase1.ext.resource

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes

/**
 * resource
 * @author FaQiang on 2018/8/28 下午6:49
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
inline fun Resources.getDraw(@DrawableRes id: Int): Drawable? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        return getDrawable(id, null)
    } else {
        return getDrawable(id)
    }
}

inline fun Resources.getColorById(@ColorRes id: Int): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return getColor(id, null)
    } else {
        return getColor(id)
    }
}