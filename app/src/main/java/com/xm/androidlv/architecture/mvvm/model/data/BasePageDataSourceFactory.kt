package com.xm.androidlv.architecture.mvvm.model.data

import androidx.paging.DataSource
import com.xm.androidlv.architecture.mvvm.model.repository.BasePageRepository


/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class BasePageDataSourceFactory<T>(private val repo: BasePageRepository<T>) :
    DataSource.Factory<Int, T>() {
    var dataSource: BasePageDataSource<T>? = null
    override fun create(): DataSource<Int, T> {
        dataSource = BasePageDataSource(repo)
        return dataSource!!
    }
}