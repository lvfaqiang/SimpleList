package com.fq.kotlinbase1.ext.content

/**
 * Context
 * @author FaQiang on 2018/8/28 上午12:02
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.res.TypedArray
import android.support.annotation.AttrRes
import android.support.annotation.LayoutRes
import android.support.annotation.RequiresApi
import android.support.annotation.StyleRes
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View

/**
 * Return the handle to a system-level service by class.
 *
 * The return type of this function intentionally uses a
 * [platform type](https://kotlinlang.org/docs/reference/java-interop.html#null-safety-and-platform-types)
 * to allow callers to decide whether they require a service be present or can tolerate its absence.
 *
 * @see Context.getSystemService(Class)
 */
@RequiresApi(23)
@Suppress("HasPlatformType") // Intentionally propagating platform type with unknown nullability.
inline fun <reified T> Context.systemService() = getSystemService(T::class.java)

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the attribute
 * values in [set] that are listed in [attrs]. In addition, if the given [AttributeSet]
 * specifies a style class (through the `style` attribute), that style will be applied
 * on top of the base attributes it defines.
 *
 * @param set The base set of attribute values.
 * @param attrs The desired attributes to be retrieved. These attribute IDs must be
 *              sorted in ascending order.
 * @param defStyleAttr An attribute in the current theme that contains a reference to
 *                     a style resource that supplies defaults values for the [TypedArray].
 *                     Can be 0 to not look for defaults.
 * @param defStyleRes A resource identifier of a style resource that supplies default values
 *                    for the [TypedArray], used only if [defStyleAttr] is 0 or can not be found
 *                     in the theme. Can be 0 to not look for defaults.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
        set: AttributeSet? = null,
        attrs: IntArray,
        @AttrRes defStyleAttr: Int = 0,
        @StyleRes defStyleRes: Int = 0,
        block: TypedArray.() -> Unit
) {
    val typedArray = obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the the values
 * defined by the style resource [resourceId] which are listed in [attrs].
 *
 * @param attrs The desired attributes. These attribute IDs must be sorted in ascending order.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
        @StyleRes resourceId: Int,
        attrs: IntArray,
        block: TypedArray.() -> Unit
) {
    val typedArray = obtainStyledAttributes(resourceId, attrs)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}

/**
 *  Intent 跳转相关
 */

inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    startActivity(
            Intent(this, T::class.java)
                    .apply {
                        block.invoke(this)
                    }
    )
}

inline fun Context.startAction(action: String, block: Intent.() -> Unit = {}) {
    startActivity(
            Intent(action)
                    .apply {
                        block.invoke(this)
                    })
}

inline fun Context.startIntent(block: Intent.() -> Unit = {}) {
    startActivity(
            Intent().apply(block)
    )
}
// ----------------------------------

// LayoutInflater

inline fun Context.inflaterView(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(this).inflate(layoutId, null)
}


// ----------------------------------

// PackageInfo

inline val Context.packageInfo: PackageInfo?
    get() = packageManager.getPackageInfo(packageName, 0)

inline val Context.versionName: String?
    get() = packageInfo?.versionName

inline val Context.versionCode: Int
    get() = packageInfo?.versionCode ?: -1


inline val Context.appDisplayMetrics: DisplayMetrics
    get() = this.applicationContext.resources.displayMetrics

/**
 * Value of dp to value of px.
 *
 * @param dpValue The value of dp.
 * @return value of px
 */
fun Context.dp2px(dpValue: Float): Int {
    val scale = this.appDisplayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * Value of px to value of dp.
 *
 * @param pxValue The value of px.
 * @return value of dp
 */
fun Context.px2dp(pxValue: Float): Int {
    val scale = this.appDisplayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}


/**
 * Value of sp to value of px.
 *
 * @param spValue The value of sp.
 * @return value of px
 */
fun Context.sp2px(spValue: Float): Int {
    val fontScale = this.appDisplayMetrics.density
    return (spValue * fontScale + 0.5f).toInt()
}

/**
 * Value of px to value of sp.
 *
 * @param pxValue The value of px.
 * @return value of sp
 */
fun Context.px2sp(pxValue: Float): Int {
    val fontScale = this.appDisplayMetrics.density
    return (pxValue / fontScale + 0.5f).toInt()
}


inline val Context.screenHeight
    get() = this.resources.displayMetrics.heightPixels


inline val Context.screenWidth
    get() = this.resources.displayMetrics.widthPixels