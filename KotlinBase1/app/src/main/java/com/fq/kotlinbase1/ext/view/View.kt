@file:Suppress("NOTHING_TO_INLINE")

// Aliases to other public API.

package com.fq.kotlinbase1.ext.view

import android.support.annotation.DrawableRes
import android.support.annotation.Px
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * ViewExt
 * @author FaQiang on 2018/8/27 下午7:06
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */


inline var View.isVisible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

inline var View.isGone
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

inline var View.isInvisible
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

inline fun View.visible() {
    isVisible = true
}

inline fun View.gone() {
    isGone = true
}

inline fun View.invisible() {
    isInvisible = true
}

inline fun View.isShowOrGone(isShow: Boolean) {
    if (isShow) visible() else gone()
}


inline fun View.isShowOrInVisible(isShow: Boolean) {
    if (isShow) visible() else invisible()
}


inline fun View.toggleHide() {
    this.visibility = if (this.visibility == View.VISIBLE) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

fun View.toggleGone() {
    visibility = if (this.visibility == View.VISIBLE) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

/**
 * 设置 TextView 显示图标
 */
inline fun TextView.drawable(@DrawableRes res: Int?, position: Position) {
    val drawable = if (res != null) resources.getDrawable(res) else {
        null
    }
    drawable?.let {
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
    }
    when (position) {
        Position.LEFT -> {
            setCompoundDrawables(drawable, null, null, null)
        }
        Position.RIGHT -> {
            setCompoundDrawables(null, null, drawable, null)
        }
        Position.TOP -> {
            setCompoundDrawables(null, drawable, null, null)
        }
        Position.BOTTOM -> {
            setCompoundDrawables(null, null, null, drawable)
        }
    }
}

enum class Position {
    LEFT, RIGHT, TOP, BOTTOM
}


/**
 * Updates this view's padding. This version of the method allows using named parameters
 * to just set one or more axes.
 *
 * @see View.setPadding
 */
inline fun View.updatePadding(
        @Px left: Int = paddingLeft,
        @Px top: Int = paddingTop,
        @Px right: Int = paddingRight,
        @Px bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

/**
 * Sets the view's padding. This version of the method sets all axes to the provided size.
 *
 * @see View.setPadding
 */
inline fun View.setPadding(@Px size: Int) {
    setPadding(size, size, size, size)
}


/**
 * Executes [block] with the View's layoutParams and reassigns the layoutParams with the
 * updated version.
 *
 * @see View.getLayoutParams
 * @see View.setLayoutParams
 **/
inline fun View.updateLayoutParams(block: ViewGroup.LayoutParams.() -> Unit) {
    updateLayoutParams<ViewGroup.LayoutParams>(block)
}

/**
 * Executes [block] with a typed version of the View's layoutParams and reassigns the
 * layoutParams with the updated version.
 *
 * @see View.getLayoutParams
 * @see View.setLayoutParams
 **/
@JvmName("updateLayoutParamsTyped")
inline fun <reified T : ViewGroup.LayoutParams> View.updateLayoutParams(block: T.() -> Unit) {
    val params = layoutParams as T
    block(params)
    layoutParams = params
}