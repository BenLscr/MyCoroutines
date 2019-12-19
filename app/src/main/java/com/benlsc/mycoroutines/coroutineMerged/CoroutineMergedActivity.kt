package com.benlsc.mycoroutines.coroutineMerged

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.benlsc.mycoroutines.R
import com.benlsc.mycoroutines.coroutineMerged.models.Todo
import kotlinx.android.synthetic.main.coroutine_merged_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoroutineMergedActivity : AppCompatActivity() {

    private val coroutineMergedViewModel: CoroutineMergedViewModel by lazy { ViewModelProviders.of(this).get(CoroutineMergedViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_merged_activity)

        configureToolbar()

        lifecycleScope.launchWhenCreated {
            //coroutineMergedViewModel.firstTodo.observe(this@CoroutineMergedActivity, Observer { text_coroutine_merged.text = it.title })
            coroutineMergedViewModel.fetch()
        }
        lifecycleScope.launchWhenStarted {  }
        lifecycleScope.launchWhenResumed {  }
    }

    private fun configureToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private suspend fun showAnswer(todo: Todo) {
        withContext(Dispatchers.Main) {
            text_coroutine_merged.text = todo.title
        }
    }

}