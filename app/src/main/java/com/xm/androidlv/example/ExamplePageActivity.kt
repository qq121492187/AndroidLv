package com.xm.androidlv.example

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xm.androidlv.R
import com.xm.androidlv.architecture.mvvm.InjectorUtil
import com.xm.androidlv.architecture.mvvm.model.data.BasePageDataSourceFactory
import com.xm.androidlv.architecture.mvvm.ui.BaseActivity
import com.xm.androidlv.architecture.mvvm.viewmodels.BasePageViewModel
import kotlinx.android.synthetic.main.activity_example_page.*

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExamplePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        supportFragmentManager.beginTransaction().add(R.id.container,ExamplePageFragment()).commit()
    }

}