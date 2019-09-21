package com.xm.androidlv.architecture.mvvm.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        observableUI()
    }

    override fun bindLayoutResId(): Int = R.layout.fragment_base_page

    override fun initializeUI() {
        mAdapter = bindAdapter()
        rv.adapter = mAdapter
        refresh.setEnableRefresh(enabledRefresh())
        refresh.setOnRefreshListener { viewModel.refresh() }
    }

    override fun lazyLoad() {
        refresh.autoRefresh()
    }

    private fun observableUI() {
        viewModel.repoStatus.observe(this, Observer {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
        viewModel.pageData.observe(this, Observer {
            subscribeUI(it)
        })
    }

    open fun subscribeUI(list: PagedList<T>) {
        refresh.finishRefresh()
        mAdapter.submitList(list)
    }

    override fun subscribeUI(data: T) {
    }

    override fun observableUI(observer: Observer<Resource<T>>) {
    }

    open fun enabledRefresh(): Boolean = true

    abstract fun bindAdapter(): BasePageAdapter<T>

    abstract fun provideRepository(): BasePageRepository<T>
}