package com.benlsc.mycoroutines

import android.content.Context

class Injection {

    companion object {

        private fun provideTokenDataSource(context: Context): TokenDataRepository {
            val database = AppDatabase.getInstance(context)
            return TokenDataRepository(database.tokenDao())
        }

        fun provideViewModelFactory(context: Context): ViewModelFactory {
            val dataSourceToken = provideTokenDataSource(context)
            return ViewModelFactory(dataSourceToken)
        }

    }

}