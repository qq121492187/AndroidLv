package com.xm.androidlv.example

import com.xm.androidlv.architecture.mvvm.model.data.ReposData
import retrofit2.http.GET

/**
 *Create by lvhaoran
 *on 2019/9/20
 */
interface Api {
    @GET("UCenter/get_massage_index")
    suspend fun loadHome(): ReposData<ExampleData>

    @GET("UCenter/get_massage_index")
    suspend fun loadFakeHome(): ReposData<ExampleData>
}