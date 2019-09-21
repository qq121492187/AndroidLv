package com.xm.androidlv.architecture.mvvm

import android.content.Context
import com.xm.androidlv.architecture.mvvm.model.repository.BasePageRepository
import com.xm.androidlv.architecture.mvvm.model.repository.BaseRepository
import com.xm.androidlv.architecture.mvvm.viewmodels.BasePageViewModelFactory
import com.xm.androidlv.architecture.mvvm.viewmodels.BaseViewModelFactory
import com.xm.androidlv.example.ExampleRepo
import com.xm.androidlv.example.ExampleViewModel
import com.xm.androidlv.example.ExampleViewModelFactory

/**
 *Create by lvhaoran
 *on 2019/9/20
 *
 * ViewModel依赖注入（DI）工具类
 *
 */
object InjectorUtil {

    /**
     * 使用依赖注入方式创建ViewModelFactory
     * 当创建ViewModelFactory的方法改变时
     * 可以很高效且安全的变更所有对象
     */
    fun providerBaseViewModelFactory(context: Context): BaseViewModelFactory {
        val repo = BaseRepository()
        return BaseViewModelFactory(repo)
    }

    /**
     * 注入BasePageViewModelFactory
     */
    fun <T> providerPageViewModelFactory(repo: BasePageRepository<T>): BasePageViewModelFactory<T> {
        return BasePageViewModelFactory(repo)
    }

    /**
     * Example
     */
    fun providerExampleViewModelFactory(context: Context): ExampleViewModelFactory {
        val repo = ExampleRepo()
        return ExampleViewModelFactory(repo)
    }
}