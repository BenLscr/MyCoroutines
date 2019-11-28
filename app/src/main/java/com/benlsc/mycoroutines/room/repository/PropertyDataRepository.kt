package com.benlsc.mycoroutines.room.repository

import androidx.lifecycle.LiveData
import com.benlsc.mycoroutines.room.dao.PropertyDao
import com.benlsc.mycoroutines.room.models.Property

class PropertyDataRepository(private val propertyDao: PropertyDao) {

    /**
     * COROUTINE VERSION
     */

    suspend fun getProperty(id: Int): Property = propertyDao.getProperty(id)

    suspend fun insertProperty(property: Property): Long = propertyDao.insertProperty(property)

    /**
     * CLASSIC VERSION (LiveData & Executor)
     */

    /*fun getProperty(id: Int): LiveData<Property> = propertyDao.getProperty(id)

    fun insertProperty(property: Property): Long = propertyDao.insertProperty(property)*/

}