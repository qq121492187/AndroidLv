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

    protected fun handlePageStatus(res: Resource<Any>, onNext: () -> Unit) {
        var status:Resource<Boolean>
        if(res is Resource.Success){
            if(res.data==null)
        }else{
            status = Resource.Error(res.message)
        }
        repoStatus.postValue(status)
        if (res is Resource.Success) {
            onNext.invoke()
        }
    }

}