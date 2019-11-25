package com.benlsc.mycoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val tokenDataSource: TokenDataRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(tokenDataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}