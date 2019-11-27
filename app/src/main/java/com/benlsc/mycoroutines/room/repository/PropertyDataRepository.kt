package com.benlsc.mycoroutines.room.repository

import com.benlsc.mycoroutines.room.dao.PropertyDao
import com.benlsc.mycoroutines.room.models.Property

class PropertyDataRepository(private val propertyDao: PropertyDao) {

    suspend fun getProperty(id: Int): Property = propertyDao.getProperty(id)

    suspend fun insertProperty(property: Property): Long = propertyDao.insertProperty(property)

}