package com.xm.androidlv.example

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.xm.androidlv.R
import com.xm.androidlv.architecture.mvvm.InjectorUtil
import com.xm.androidlv.architecture.mvvm.ui.BaseActivity
import com.xm.androidlv.net.resource.Resource

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExampleActivity : BaseActivity<ExampleData>() {

    private val viewModel: ExampleViewModel by viewModels {
        InjectorUtil.providerExampleViewModelFactory(this)
    }

    override fun bindLayoutResId(): Int = R.layout.activity_main

    override fun initializeUI() {
        viewBinding.tv.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ExamplePageActivity::class.java
                )
            )
        }
    }

    override fun observableUI(observer: Observer<Resource<ExampleData>>) {
        viewModel.exampleData.observe(this, observer)
    }

    override fun subscribeUI(data: ExampleData) {
        viewBinding.data = data

    }
}