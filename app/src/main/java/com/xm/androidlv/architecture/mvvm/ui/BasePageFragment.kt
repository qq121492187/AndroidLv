package com.xm.androidlv.architecture.mvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.xm.androidlv.R
import com.xm.androidlv.adapter.BasePageAdapter
import com.xm.androidlv.architecture.mvvm.InjectorUtil
import com.xm.androidlv.architecture.mvvm.model.repository.BasePageRepository
import com.xm.androidlv.architecture.mvvm.viewmodels.BasePageViewModel
import com.xm.androidlv.net.resource.Resource
import kotlinx.android.synthetic.main.fragment_base_page.*

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
abstract class BasePageFragment<T> : BaseFragment<T>() {

    private lateinit var mAdapter: BasePageAdapter<T>

    private val viewModel: BasePageViewModel<T> by viewModels {
        InjectorUtil.providerPageViewModelFactory(provideRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun bindLayoutResId(): Int = R.layout.fragment_base_page

    override fun initializeUI() {
        mAdapter = bindAdapter()
        rv.adapter = mAdapter
        refresh.setEnableRefresh(enabledRefresh())
        refresh.setOnRefreshListener { viewModel.refresh() }
    }


    private fun observableUI() {
        viewModel.repoStatus.observe(this, Observer {
            subscribeStatus(it)
        })
        viewModel.pageData.observe(this, Observer {
            subscribeUI(it)
        })
    }

    open fun subscribeUI(list: PagedList<T>) {
        mAdapter.submitList(list)
    }

    private fun subscribeStatus(status: Resource<Boolean>) {
        when (status) {
            is Resource.Success -> {
                refresh.finishRefresh()
                if (status.data!!) {
                    //加载成功
                    onPageLoaded()
                } else {
                    //无数据
                    onPageEmpty()
                }
            }
            is Resource.Loading -> {
                onPageLoading()
            }
            is Resource.Error -> {
                onPageError(status.data!!)
            }
        }
    }

    open fun onPageEmpty() {
        Log.e("page","empty")
    }

    open fun onPageError(data: Boolean) {
        if(data){
            //初始数据异常
            Log.e("page","init error")
        }else{
            //分页数据异常
        }
        Log.e("page","loadmore error")
    }

    open fun onPageLoading() {
        refresh.autoRefreshAnimationOnly()
    }

    open fun onPageLoaded() {
        Log.e("page","loaded")
    }

    override fun subscribeUI(data: T) {
    }

    override fun observableUI(observer: Observer<Resource<T>>) {
        observableUI()
    }

    open fun enabledRefresh(): Boolean = true

    abstract fun bindAdapter(): BasePageAdapter<T>

    abstract fun provideRepository(): BasePageRepository<T>
}