package com.xm.androidlv.architecture.mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xm.androidlv.databinding.ActivityMainBinding
import com.xm.androidlv.net.resource.Resource

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
abstract class BaseFragment<T> : Fragment() {

    protected lateinit var viewBinding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =
            DataBindingUtil.inflate(inflater, bindLayoutResId(), container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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