package com.xm.androidlv.architecture.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.xm.androidlv.architecture.mvvm.model.repository.BaseRepository

/**
 *Create by lvhaoran
 *on 2019/9/20
 *
 * ViewModel负责处理数据和业务逻辑
 * 不可持有view和Context的引用
 *
 * 根据依赖反转原则 一个类的依赖项不应由此类创建，而应该通过构造函数传入
 * 或使用抽象
 */
open class BaseViewModel(private var repos: BaseRepository) : ViewModel() {

    open fun withModelClearRepos(): Boolean = true

    override fun onCleared() {
        super.onCleared()
        if (withModelClearRepos()) {
            repos.clear()
        }
    }
}