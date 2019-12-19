package com.benlsc.mycoroutines

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benlsc.mycoroutines.room.models.Address
import com.benlsc.mycoroutines.room.models.Agent
import com.benlsc.mycoroutines.room.models.Property
import com.benlsc.mycoroutines.room.repository.AddressDataRepository
import com.benlsc.mycoroutines.room.repository.AgentDataRepository
import com.benlsc.mycoroutines.room.repository.PropertyDataRepository
import kotlinx.coroutines.*
import org.w3c.dom.Document
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor
import javax.xml.parsers.DocumentBuilderFactory

class MainViewModel(
    private val addressDataSource: AddressDataRepository,
    private val agentDataSource: AgentDataRepository,
    private val propertyDataSource: PropertyDataRepository,
    private val executor: Executor
): ViewModel() {

    /*---------------ROOM----------------*/

    /**
     * COROUTINE VERSION
     */

    val propertyLiveData = MutableLiveData<Property>()

    fun tryCoroutineWithRoom() {
        val address = Address(postalCode = 76510)
        val agent = Agent(name = "Albert", surname = "Einstein")
        viewModelScope.launch(Dispatchers.Default) {
            val propertyId = insertData(address, agent)
            val property = retrieveData(propertyId)
            withContext(Dispatchers.Main) {
            propertyLiveData.value = property
            }
        }
    }

    private suspend fun insertData(address: Address, agent: Agent): Long {
        val addressId = addressDataSource.insertAddress(address)
        val agentId = agentDataSource.insertAgent(agent)
        val property = Property(addressId = addressId, agentId = agentId, description = "Very long description")
        return propertyDataSource.insertProperty(property)
    }

    private suspend fun retrieveData(propertyId: Long) = propertyDataSource.getProperty(propertyId.toInt())

    /**
     * CLASSIC VERSION (LiveData & Executor)
     */

    /*val propertyLiveData = MutableLiveData<Property>()

    fun tryClassicWithRoom() {
        val address = Address(postalCode = 76510)
        val agent = Agent(name = "Albert", surname = "Einstein")
        executor.execute {
            val propertyId = insertData(address, agent)
            val property = retrieveData(propertyId)
            propertyLiveData.postValue(property.value)
        }
    }

    private fun insertData(address: Address, agent: Agent): Long {
        val addressId = addressDataSource.insertAddress(address)
        val agentId = agentDataSource.insertAgent(agent)
        val property = Property(addressId = addressId, agentId = agentId, description = "Very long description")
        return propertyDataSource.insertProperty(property)
    }

    private fun retrieveData(propertyId: Long) = propertyDataSource.getProperty(propertyId.toInt())*/

    /*---------------RSS----------------*/

    val document = MutableLiveData<Document>()

    fun fetchDoc() {
        viewModelScope.launch {
            getRssFromWikipedia()
        }
    }

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

    /*---------------CRASH TEST----------------*/

    /*fun testManyCoroutines() {
        viewModelScope.launch { myFirstCoroutine() }
        viewModelScope.launch { mySecondCoroutine() }
        viewModelScope.launch { myThirdCoroutine() }
    }*/

    fun testManyCoroutines() {
        viewModelScope.launch {
            myFirstCoroutine()
            mySecondCoroutine()
            myThirdCoroutine()
        }
    }

    private suspend fun myFirstCoroutine() {
        delay(3000)
        Log.e("FirstCoroutine,","test")
    }

    private suspend fun mySecondCoroutine() {
        Log.e("SecondCoroutine", "test")
    }
    private suspend fun myThirdCoroutine() {
        delay(1000)
        Log.e("ThirdCoroutine", "test")
    }

    /**
     * Runblocking
     */

    fun testRunBlocking() = runBlocking {
        delay(5000)
        Log.e("runBlocking", "test ended")
    }



}