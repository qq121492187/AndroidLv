package com.xm.androidlv.architecture.mvvm.model.repository

import com.xm.androidlv.example.Api
import com.xm.androidlv.net.NetFactory

/**
 *Create by lvhaoran
 *on 2019/9/20
 */
class ApiService private constructor() {

    companion object {
        @Volatile
        private var instance: ApiService? = null

        fun getInstance(): ApiService {
            return instance ?: synchronized(this) {
                instance ?: ApiService()
            }
        }
    }

    fun getService(): Api {
        return NetFactory.createService(Api::class.java)
    }

}