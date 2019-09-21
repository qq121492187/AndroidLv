package com.xm.androidlv.architecture.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xm.androidlv.architecture.mvvm.model.repository.BasePageRepository

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class BasePageViewModelFactory<F>(private val repo: BasePageRepository<F>) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BasePageViewModel(repo) as T
    }
}