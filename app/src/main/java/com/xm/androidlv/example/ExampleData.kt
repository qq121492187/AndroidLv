package com.xm.androidlv.example

/**
 *Create by lvhaoran
 *on 2019/9/21
 */
data class ExampleData(val icon_list: List<IconList>) {
    data class IconList(val icon: String, val title: String, val count: String)
}