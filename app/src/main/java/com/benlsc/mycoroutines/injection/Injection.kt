package com.benlsc.mycoroutines.injection

import android.content.Context
import com.benlsc.mycoroutines.room.AppDatabase
import com.benlsc.mycoroutines.room.repository.AddressDataRepository
import com.benlsc.mycoroutines.room.repository.AgentDataRepository
import com.benlsc.mycoroutines.room.repository.PropertyDataRepository
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Injection {

    companion object {

        private fun provideAddressDataSource(context: Context): AddressDataRepository {
            val database = AppDatabase.getInstance(context)
            return AddressDataRepository(database.addressDao())
        }

        private fun provideAgentDataSource(context: Context): AgentDataRepository {
            val database = AppDatabase.getInstance(context)
            return AgentDataRepository(database.agentDao())
        }

        private fun providePropertyDataSource(context: Context): PropertyDataRepository {
            val database = AppDatabase.getInstance(context)
            return PropertyDataRepository(database.propertyDao())
        }

        private fun provideExecutor() : Executor {
            return Executors.newSingleThreadExecutor()
        }

        fun provideViewModelFactory(context: Context): ViewModelFactory {
            val dataSourceAddress = provideAddressDataSource(context)
            val dataSourceAgent = provideAgentDataSource(context)
            val dataSourceProperty = providePropertyDataSource(context)
            val executor = provideExecutor()
            return ViewModelFactory(
                dataSourceAddress,
                dataSourceAgent,
                dataSourceProperty,
                executor
            )
        }

    }

}