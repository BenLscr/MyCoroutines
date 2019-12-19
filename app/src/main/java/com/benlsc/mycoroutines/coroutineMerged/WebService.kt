package com.benlsc.mycoroutines.coroutineMerged

import com.benlsc.mycoroutines.coroutineMerged.models.TheNewYorkTimesResponse
import com.benlsc.mycoroutines.coroutineMerged.models.Todo
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Webservice {

    @GET("topstories/v2/{section}.json")
    suspend fun getTopStories(@Path(value = "section") section: String, @Query(value = "api-key") apiKey: String): TheNewYorkTimesResponse

    @GET("mostpopular/v2/viewed/{period}.json")
    suspend fun getMostPopular(@Path(value = "period") period: String, @Query(value = "api-key") apiKey: String): TheNewYorkTimesResponse

    @GET("/todos/{id}")
    suspend fun getTodo(@Path(value = "id") todoId: Int): Todo

    companion object {
        val webservice: Webservice by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(Webservice::class.java)
        }
    }

}