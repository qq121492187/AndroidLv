package com.xm.androidlv.net

/**
 *Create by lvhaoran
 *on 2019/8/2
 */
class NetConfig {
    companion object {
        const val BASE_URL = "http://api.dp.xiaomei.cn/"
        const val BASE_IMAGE_URL = "http://101.200.207.39:8181/yzApi/img/"
    }

    enum class NetStatus {
        NET_SUCCESS,
        NET_ERROR,
        NET_DONE
    }
}