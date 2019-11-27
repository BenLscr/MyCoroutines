package com.benlsc.mycoroutines.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.benlsc.mycoroutines.room.models.Property

@Dao
interface PropertyDao {

    @Query("SELECT * FROM PROPERTY INNER JOIN Address ON Property.addressId = Address.address_id INNER JOIN Agent ON Property.agentId = Agent.agent_id WHERE :id = id")
    suspend fun getProperty(id: Int): Property

    @Insert
    suspend fun insertProperty(property: Property): Long

}