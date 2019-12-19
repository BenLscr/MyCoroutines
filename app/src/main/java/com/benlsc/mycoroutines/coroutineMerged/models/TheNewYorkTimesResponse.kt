package com.benlsc.mycoroutines.coroutineMerged.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TheNewYorkTimesResponse {
    //TOP STORIES & MOST POPULAR\\
    @SerializedName("results")
    @Expose
    var articles: List<Article>? = null
}