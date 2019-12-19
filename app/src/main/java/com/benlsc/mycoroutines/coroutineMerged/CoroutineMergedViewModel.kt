package com.benlsc.mycoroutines.coroutineMerged

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.benlsc.mycoroutines.coroutineMerged.models.TheNewYorkTimesResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.*

class CoroutineMergedViewModel : ViewModel() {

    private val repository: TodoRepository = TodoRepository()
    private val api: Webservice = Webservice.webservice

    val firstTodo = liveData(Dispatchers.IO) {
        val retrivedTodo = repository.getTodo(1)

        emit(retrivedTodo)
    }

    fun fetch() {
        viewModelScope.launch {
            try {
                val articles = withContext(Dispatchers.IO) {
                    api.getTopStories("home", "4cKaGJtqJJDtrVx14QNFiGbfQI6tqEP6")
                }
                showArticles(articles)
            } catch (e:Exception) {
                //showError(e)
                Log.e("catch", "here")
            }
        }
    }

    private suspend fun showArticles(articles: TheNewYorkTimesResponse) {
        withContext(Dispatchers.Main) {
            Log.e("Articles", articles.articles?.size.toString())
            Log.e("Articles", "here")
        }
    }

}
