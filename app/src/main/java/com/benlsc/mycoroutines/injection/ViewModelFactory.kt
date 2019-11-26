package com.benlsc.mycoroutines.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benlsc.mycoroutines.MainViewModel
import com.benlsc.mycoroutines.room.TokenDataRepository
import java.util.concurrent.Executor

class ViewModelFactory(
    private val tokenDataSource: TokenDataRepository,
    private val executor: Executor
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                tokenDataSource,
                executor
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}