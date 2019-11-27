package com.benlsc.mycoroutines.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benlsc.mycoroutines.MainViewModel
import com.benlsc.mycoroutines.room.repository.AddressDataRepository
import com.benlsc.mycoroutines.room.repository.AgentDataRepository
import com.benlsc.mycoroutines.room.repository.PropertyDataRepository
import java.util.concurrent.Executor

class ViewModelFactory(
    private val addressDataSource: AddressDataRepository,
    private val agentDataSource: AgentDataRepository,
    private val propertyDataSource: PropertyDataRepository,
    private val executor: Executor
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                addressDataSource,
                agentDataSource,
                propertyDataSource,
                executor
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}