package com.benlsc.mycoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val propertyListViewModel : MainViewModel by lazy { ViewModelProviders.of(this, Injection.provideViewModelFactory(applicationContext)).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addListener()

    }



    private fun addListener() {
        add_tokens.setOnClickListener {
            propertyListViewModel.setTokens(100)
        }
        show_token.setOnClickListener {
            propertyListViewModel.getToken(42).observe(this, Observer { id_token.text = it })
        }
    }

}
