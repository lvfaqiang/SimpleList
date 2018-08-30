package com.fq.kotlinbase1.api

import android.net.ParseException
import android.os.Parcelable
import com.fq.kotlinbase1.App
import com.fq.kotlinbase1.R
import com.google.gson.Gson
import com.google.gson.JsonParseException
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * ApiException
 * @author FaQiang on 2018/8/28 上午11:26
 * @Github: <a href="https://github.com/lvfaqiang"/>
 * @Blog: <a href="http://blog.csdn.net/lv_fq"/>
 * @desc :
 *
 */
class ApiException(e: Throwable) {

    private var code: Int = 0
    private var httpCode: Int = 0
    private var message: String = ""
    private var errorBody: HttpErrorBody? = null


    init {
        when (e) {
            is HttpException -> {
                httpCode = e.code()
                val string = e.response().errorBody()?.string() ?: ""
                try {
                    errorBody = Gson().fromJson(string, HttpErrorBody::class.java)
                } catch (e: Exception) {
                    println("ApiException : ${e.message}")
                }
                errorBody?.let {
                    code = it.error?.code ?: code
                    message = it.error?.message ?: message
                }

                when (httpCode) {
                    401 -> {
                        if (code == 40001 || code == 42001) {
//                            UserExt.loginOut()
                        }
                    }
                    503 -> {
                        message = App.mContext.getString(R.string.str_exception_server_maintance)
                    }
                    in 500 until 600 -> {
                        message = App.mContext.getString(R.string.str_exception_server)
                    }
                }


            }
            is JsonParseException,
            is ParseException,
            is JSONException -> {
                message = App.mContext.getString(R.string.str_exception_parse)
            }
            is ConnectException -> {
                message = App.mContext.getString(R.string.str_exception_network)
            }
            is SocketTimeoutException -> {
                message = App.mContext.getString(R.string.str_request_timeout)
            }
            else -> {
                message = App.mContext.getString(R.string.str_exception_unknown)
                println("ApiException unknown Exception : ${e.message}")
            }
        }
    }

    fun getCode() = code

    fun getHttpCode() = httpCode

    fun getMessage() = message


}

data class HttpErrorBody(var error: Error?)

@Parcelize
data class Error(var code: Int, var message: String = "") : Parcelable