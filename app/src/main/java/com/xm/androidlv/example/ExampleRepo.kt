package com.xm.androidlv.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xm.androidlv.architecture.mvvm.model.repository.BaseRepository
import com.xm.androidlv.net.resource.Resource
import kotlinx.coroutines.launch

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExampleRepo : BaseRepository() {
    fun loadExample(): LiveData<Resource<ExampleData>> {
        val data = MutableLiveData<Resource<ExampleData>>()
        data.value = Resource.Loading()
        launch {
            val res = repos { service.loadHome() }
            data.postValue(res)
        }
        return data
    }
}