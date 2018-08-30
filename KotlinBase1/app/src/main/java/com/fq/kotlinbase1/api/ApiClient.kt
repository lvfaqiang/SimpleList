package com.fq.kotlinbase1.api

import com.fq.kotlinbase1.BuildConfig
import com.lvfq.library.utils.LvLog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * ApiClient
 * @author FaQiang on 2018/8/28 下午5:00
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */

class ApiClient {
    companion object {
        fun get(): ApiClient {
            return SingletonHolder.INSTANCE
        }

        fun getDefault(): ApiService {
            return get().DEFAULT
        }
    }

    private object SingletonHolder {
        internal val INSTANCE = ApiClient()
    }


    private val mRetrofit by lazy {
        Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(ApiConfig().configOkHttp())
                .build()
    }

    private val DEFAULT by lazy { mRetrofit.create(ApiService::class.java) }

    fun <T> create(klass: Class<T>): T = mRetrofit.create(klass)
}

interface ApiService {

}

class ApiConfig {
    private val TIME_OUT = 10L  // 默认请求时间 10s

    val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String?) {
            takeIf { BuildConfig.DEBUG }?.let {
                LvLog.i("OKHTTP", message)
            }
        }
    }).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun configOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logger)  // 网络请求日志

        // 配置超时时间
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)

        return builder.build()
    }
}