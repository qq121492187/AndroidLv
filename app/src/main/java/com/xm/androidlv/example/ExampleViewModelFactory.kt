package com.xm.androidlv.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExampleViewModelFactory(private val repo: ExampleRepo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExampleViewModel(repo) as T
    }
}