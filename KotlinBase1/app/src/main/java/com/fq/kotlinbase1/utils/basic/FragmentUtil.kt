package com.fq.kotlinbase1.utils.basic

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * FragmentUtil
 * @author FaQiang on 2018/9/27 下午12:12
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class FragmentUtil constructor(val manager: FragmentManager) {


    private var curFragment: Fragment? = null

    /**
     * 显示 Fragment
     *
     * @param fragment
     * @param id
     */
    fun showFragment(fragment: Fragment, id: Int) {
        val ft = manager.beginTransaction()
        if (curFragment != null) {
            ft.hide(curFragment)
        }
        if (fragment.isAdded) {
            ft.show(fragment)
        } else {
            ft.add(id, fragment).show(fragment)
        }
        ft.commitAllowingStateLoss()
        curFragment = fragment
    }

    /**
     * 替换 Fragment
     *
     * @param fragment
     * @param id
     */
    fun replaceFragment(fragment: Fragment, id: Int) {
        val ft = manager.beginTransaction()
        ft.replace(id, fragment)
        ft.commitAllowingStateLoss()
    }

    fun clear() {
        val ft = manager.beginTransaction()
        manager.fragments?.map {
            ft?.remove(it)
        }
        ft?.commitAllowingStateLoss()
    }
}