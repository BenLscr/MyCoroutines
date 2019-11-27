package com.benlsc.mycoroutines.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.benlsc.mycoroutines.room.models.Address

@Dao
interface AddressDao {

    @Query("SELECT * FROM Address WHERE :id = address_id")
    fun getAddress(id: Int): LiveData<Address>

    @Insert
    suspend fun insertAddress(address: Address): Long

}