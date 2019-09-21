package com.xm.androidlv.example

import com.xm.androidlv.adapter.BasePageAdapter
import com.xm.androidlv.architecture.mvvm.model.repository.BasePageRepository
import com.xm.androidlv.architecture.mvvm.ui.BasePageFragment

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExamplePageFragment : BasePageFragment<ExampleData.IconList>() {

    override fun bindAdapter(): BasePageAdapter<ExampleData.IconList> {
        return ExamplePageAdapter(requireContext())
    }

    override fun provideRepository(): BasePageRepository<ExampleData.IconList> {
        return ExamplePageRepo()
    }

}