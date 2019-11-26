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
        add_tokens.setOnClickListener {
            mainViewModel.setTokens(100)
        }
        show_token.setOnClickListener {
            mainViewModel.getToken(42).observe(this, Observer { id_token.text = it })
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
    }

    private fun wikipediaAsynTask() {
        val adapter = RSSAdapter()
        val asyncTask = XMLAsyncTask(adapter)
        asyncTask.execute()
    }

}
