package com.benlsc.mycoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.Executor

class ViewModelFactory(
    private val tokenDataSource: TokenDataRepository,
    private val executor: Executor
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(tokenDataSource,
                executor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}