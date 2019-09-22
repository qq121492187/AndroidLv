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
        //发送加载中的通知，在初始化数据时通知
        //分页数据的加载过程不需要通知，由paging负责控制
        repoStatus.postValue(Resource.Loading())
        launch {
            //请求数据
            val res = repos { service.loadHome() }
            //处理请求数据
            handlePageStatus(res, res.data?.icon_list, true) {
                icons.addAll(it)
                //处理列表
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
            handlePageStatus(res, res.data?.icon_list) {
                icons.addAll(it)
                onPage.invoke(icons)
            }
        }
    }
}