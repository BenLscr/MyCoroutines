package com.benlsc.mycoroutines

import android.util.Log
import androidx.lifecycle.*
import com.benlsc.mycoroutines.room.Token
import com.benlsc.mycoroutines.room.TokenDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Document
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import javax.xml.parsers.DocumentBuilderFactory

class MainViewModel(
    private val tokenDataSource: TokenDataRepository,
    private val executor: Executor
): ViewModel() {

    /*---------------ROOM----------------*/

    fun getToken(id: Int): LiveData<String> =
        Transformations.map(tokenDataSource.getToken(id)) {
            it?.id?.toString() ?: "no token found"
        }

    fun setTokens(howMany: Int) {
        executor.execute {
            var id: Int = 0
            do {
                id++
                val token = Token(id)
                tokenDataSource.insertToken(token)
            } while (id != howMany)
        }
    }

    fun fetchDoc() {
        viewModelScope.launch {
            getRssFromWikipedia()
        }
    }

    /*---------------RSS----------------*/

    val document = MutableLiveData<Document>()

    private suspend fun getRssFromWikipedia() {
        withContext(Dispatchers.IO) {
            val url = URL("https://fr.wikipedia.org/w/api.php?hidebots=1&days=7&limit=50&hideWikibase=1&action=feedrecentchanges&feedformat=rss")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val stream: InputStream = connection.inputStream
            var doc: Document? = null
            stream.use {
                doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(it)
            }
            withContext(Dispatchers.Main) {
                Log.e("Document task", "Finished")
                document.value = doc
            }
        }
    }

}