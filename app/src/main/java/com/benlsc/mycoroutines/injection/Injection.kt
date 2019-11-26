package com.benlsc.mycoroutines.injection

import android.content.Context
import com.benlsc.mycoroutines.room.TokenDataRepository
import com.benlsc.mycoroutines.room.AppDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Injection {

    companion object {

        private fun provideTokenDataSource(context: Context): TokenDataRepository {
            val database = AppDatabase.getInstance(context)
            return TokenDataRepository(database.tokenDao())
        }

        private fun provideExecutor() : Executor {
            return Executors.newSingleThreadExecutor()
        }

        fun provideViewModelFactory(context: Context): ViewModelFactory {
            val dataSourceToken =
                provideTokenDataSource(
                    context
                )
            val executor =
                provideExecutor()
            return ViewModelFactory(
                dataSourceToken,
                executor
            )
        }

    }

}