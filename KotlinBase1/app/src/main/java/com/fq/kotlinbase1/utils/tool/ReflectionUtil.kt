package com.fq.kotlinbase1.utils.tool

import java.lang.reflect.Method

/**
 * ReflectionUtil
 * @author FaQiang on 2018/9/11 下午6:22
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *      反射工具类
 *
 */
object ReflectionUtil {
    fun setFieldValue(obj: Any?, fieldName: String, value: Any?) {
        if (obj == null || fieldName.isEmpty()) {
            return
        }
        val clazz: Class<*> = obj.javaClass
        while (clazz != Any::class.java) {
            try {
                val field = clazz.getDeclaredField(fieldName)
                field.isAccessible = true
                field.set(obj, value)
                return
            } catch (e: Exception) {
            }
        }
    }

    fun getFieldValue(obj: Any?, fieldName: String): Any? {
        if (obj == null || fieldName.isEmpty()) {
            return null
        }
        val clazz: Class<*> = obj.javaClass
        if (clazz != Any::class.java) {
            try {
                val field = clazz.getDeclaredField(fieldName)
                field.isAccessible = true
                return field.get(obj)
            } catch (e: Exception) {
            }
        }
        return null
    }

    /**
     * https://blog.csdn.net/zmx729618/article/details/51320688
     *
     *  获取当前类型的 某个方法
     */
    fun getDeclaredMethod(obj: Any, methodName: String, vararg parameterTypes: Class<*>): Method? {
        var method: Method? = null
        var clazz: Class<*> = obj.javaClass
        while (clazz != Any::class.java) {
            try {
                method = clazz.getDeclaredMethod(methodName, *parameterTypes)
//                method?.isAccessible = true
                return method
            } catch (e: Exception) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
            clazz = clazz.superclass
        }
        return null
    }
}