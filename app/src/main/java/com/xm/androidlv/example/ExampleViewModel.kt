package com.xm.androidlv.example

import androidx.lifecycle.ViewModel

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
class ExampleViewModel(
    repo: ExampleRepo
) : ViewModel() {
    val exampleData = repo.loadExample()
}