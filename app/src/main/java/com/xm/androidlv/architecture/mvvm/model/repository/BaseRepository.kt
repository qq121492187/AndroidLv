package com.xm.androidlv.architecture.mvvm.model.repository

import com.xm.androidlv.architecture.mvvm.model.data.ReposData
import com.xm.androidlv.conf.Config
import com.xm.androidlv.net.resource.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 *Create by lvhaoran
 *on 2019/9/20
 *
 * 需要根据业务自定义
 */
open class BaseRepository : CoroutineScope by MainScope() {
    protected val service = ApiService.getInstance().getService()

    suspend fun <T> repos(b: suspend () -> ReposData<T>): Resource<T> {
        try {
            val reposData = b.invoke()
            if (reposData.success == Config.configs["repos_success_code"]) {
                return Resource.Success(reposData.list)
            }
            return Resource.Error(reposData.msg)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
    }

    /**
     * 清除所有请求
     */
    fun clear() {
        cancel()
    }
}