package com.xm.androidlv.architecture.mvvm.model.data

/**
 *Create by lvhaoran
 *on 2019/9/20
 *
 * 数据获取封装的基类  根据与后端约定的形式封装
 */
data class ReposData<T>(val success:Int,val msg:String,val list:T)