package com.xm.androidlv.net

import com.xm.network.intercept.CommonParamsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *Create by lvhaoran
 *on 2019/9/20
 */
class NetFactory {
    companion object {
        fun <T> createService(clz: Class<T>): T {
            val retrofit = Retrofit.Builder().client(getOkHttpClient())
                .baseUrl(NetConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(clz)
        }

        private fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(CommonParamsInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }
    }
}