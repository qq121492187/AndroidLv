package com.xm.androidlv.architecture.mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.xm.androidlv.databinding.ActivityMainBinding
import com.xm.androidlv.net.resource.Resource

/**
 *Create by lvhaoran
 *on 2019/9/20
 *
 * activity基类
 */
abstract class BaseActivity<T> : AppCompatActivity() {

    protected lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, bindLayoutResId())
        initializeUI()
        val uiObservable = Observer<Resource<T>> {
            handleResource(it)
        }
        observableUI(uiObservable)
    }

    abstract fun bindLayoutResId(): Int
    abstract fun initializeUI()
    abstract fun observableUI(observer: Observer<Resource<T>>)
    abstract fun subscribeUI(data: T)
    fun subscribeLoading() {}
    fun subscribeError(message: String) {}


    fun handleResource(resource: Resource<T>) {
        when (resource) {
            is Resource.Loading -> {
                subscribeLoading()
            }
            is Resource.Success -> {
                subscribeUI(resource.data!!)
            }
            is Resource.Error -> {
                subscribeError(resource.message)
            }
        }
    }
}