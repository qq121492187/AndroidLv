package com.xm.androidlv.example

import androidx.lifecycle.Transformations
import com.xm.androidlv.architecture.mvvm.model.repository.BasePageRepository
import com.xm.androidlv.net.resource.Resource
import kotlinx.coroutines.launch

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExamplePageRepo : BasePageRepository<ExampleData.IconList>() {

    override fun pageInit(
        initSize: Int,
        onPage: (list: List<ExampleData.IconList>) -> Unit
    ) {
        val icons = mutableListOf<ExampleData.IconList>()
        repoStatus.postValue(Resource.Loading())
        launch {
            val res = repos { service.loadHome() }
            handlePageStatus(res) {
                icons.addAll(res.data?.icon_list!!)
                onPage.invoke(icons)
            }
        }
    }


    override fun getDataByPage(
        page: Int,
        size: Int,
        onPage: (list: List<ExampleData.IconList>) -> Unit
    ) {
        val icons = mutableListOf<ExampleData.IconList>()
        launch {
            val res = repos { service.loadFakeHome() }
            handlePageStatus(res) {
                icons.addAll(res.data?.icon_list!!)
                onPage.invoke(icons)
            }
        }
    }
}