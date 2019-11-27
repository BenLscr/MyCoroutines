package com.benlsc.mycoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.benlsc.mycoroutines.async.XMLAsyncTask
import com.benlsc.mycoroutines.injection.Injection
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private val mainViewModel : MainViewModel by lazy { ViewModelProviders.of(this, Injection.provideViewModelFactory(applicationContext)).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addListener()

        addObserver()
    }

    private fun addListener() {
        try_database.setOnClickListener {
            mainViewModel.tryCoroutineWithRoom()
        }
        dl_wikipedia.setOnClickListener {
            mainViewModel.fetchDoc()
        }
    }

    private fun addObserver() {
        mainViewModel.document.observe(this, Observer {
            Log.e("Wikipedia", it.toString())
            val adapter = RSSAdapter()
            adapter.getNewXML(it)
        })
        mainViewModel.propertyLiveData.observe(this, Observer {
            description.text = it.description
            name.text = it.agent?.name
            surname.text = it.agent?.surname
            postalcode.text = it.address?.postalCode.toString()
        })
    }

    private fun wikipediaAsynTask() {
        val adapter = RSSAdapter()
        val asyncTask = XMLAsyncTask(adapter)
        asyncTask.execute()
    }

}
