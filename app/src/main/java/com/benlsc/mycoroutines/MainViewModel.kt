package com.benlsc.mycoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executor

class MainViewModel(
    private val tokenDataSource: TokenDataRepository,
    private val executor: Executor
): ViewModel() {

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

}