package com.xm.androidlv.architecture.mvvm.model.repository

import androidx.annotation.IntRange
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.xm.androidlv.architecture.mvvm.model.data.ReposData
import com.xm.androidlv.net.resource.Resource

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
abstract class BasePageRepository<T> : BaseRepository() {

    val repoStatus by lazy { MutableLiveData<Resource<Boolean>>() }

    abstract fun pageInit(@IntRange(from = 0) initSize: Int, onPage: (list: List<T>) -> Unit)

    abstract fun getDataByPage(
        @IntRange(from = 0) page: Int, size: Int,
        onPage: (list: List<T>) -> Unit
    )

    /**
     * 处理页面数据的状态
     * @param res 要处理的数据状态
     * @param list 处理的数据集
     * @param isInit 是否为初始化的请求
     * @param onNext 状态正确无异常时的回调
     */
    protected fun handlePageStatus(
        res: Resource<Any>,
        list: List<T>? = null,
        isInit: Boolean = false,
        onNext: (list: List<T>) -> Unit
    ) {
        val status: Resource<Boolean>
        if (res is Resource.Success) {
            status = if (list.isNullOrEmpty()) {
                Resource.Success(false)
            } else {
                Resource.Success(true)
            }
        } else {
            status = Resource.Error(res.message, isInit)
        }
        repoStatus.postValue(status)
        if (status is Resource.Success && status.data!!) {
            onNext.invoke(list!!)
        }
    }

}