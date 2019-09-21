package com.xm.androidlv.net.resource

/**
 *Create by lvhaoran
 *on 2019/9/20
 */
sealed class Resource<out T>(
    val code: Int = 0,
    val message: String = "",
    val data: T? = null
) {

    class Success<T>(data: T) : Resource<T>(1, "success", data)
    class Loading<T>(data: T? = null) : Resource<T>(0, "loading", data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(2, message, data)


    fun isSuccess(): Boolean = code == 1
    fun subscribe(success: (data: T) -> Unit, error: (msg: String) -> Unit) {
        if (isSuccess())
            success.invoke(this.data!!)
        else
            error.invoke(this.message)
    }
}