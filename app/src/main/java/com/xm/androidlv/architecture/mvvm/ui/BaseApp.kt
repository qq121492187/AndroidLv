package com.xm.androidlv.architecture.mvvm.ui

import android.app.Application
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class BaseApp:Application() {

    companion object {
        private lateinit var appInstance: BaseApp
        fun getApp(): BaseApp {
            return appInstance
        }
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            return@setDefaultRefreshHeaderCreator MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            return@setDefaultRefreshFooterCreator ClassicsFooter(context)
        }
    }
}