package com.benlsc.mycoroutines.coroutineMerged.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article {
    //TOP STORIES & MOST POPULAR\\
    @SerializedName("section")
    @Expose
    var section: String? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("url")
    @Expose
    val url: String? = null
}