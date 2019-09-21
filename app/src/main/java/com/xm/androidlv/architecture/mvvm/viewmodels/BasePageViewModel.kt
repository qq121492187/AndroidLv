package com.xm.androidlv.architecture.mvvm.viewmodels

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xm.androidlv.architecture.mvvm.model.data.BasePageDataSourceFactory
import com.xm.androidlv.architecture.mvvm.model.repository.BasePageRepository
import com.xm.androidlv.example.ExamplePageRepo

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class BasePageViewModel<T>(
    private val repo: BasePageRepository<T>,
    private val config: PagedList.Config? = null
) : BaseViewModel(repo) {

    private val dataSourceFactory by lazy { BasePageDataSourceFactory(repo) }
    private val pageConfig by lazy {
        config ?: PagedList.Config.Builder()
            .setInitialLoadSizeHint(10) //初始加载数量
            .setPageSize(10)    //每页数量
            // .setMaxSize(50)   //最大数量
            .setPrefetchDistance(3) //距离底部多少个Item时开始加载下一页
            .setEnablePlaceholders(true) //是否使用null占位符
            .build()
    }

    val pageData = LivePagedListBuilder(dataSourceFactory, pageConfig).build()
    //请求状态
    val repoStatus = repo.repoStatus

    fun refresh() {
        dataSourceFactory.dataSource?.invalidate()
    }
}