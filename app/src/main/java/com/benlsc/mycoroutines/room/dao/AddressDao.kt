package com.benlsc.mycoroutines.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.benlsc.mycoroutines.room.models.Address

@Dao
interface AddressDao {

    /**
     * COROUTINE VERSION
     */

    @Query("SELECT * FROM Address WHERE :id = address_id")
    suspend fun getAddress(id: Int): Address

    @Insert
    suspend fun insertAddress(address: Address): Long

    /**
     * CLASSIC VERSION (LiveData & Executor)
     */

    /*@Query("SELECT * FROM Address WHERE :id = address_id")
    fun getAddress(id: Int): LiveData<Address>

    @Insert
    fun insertAddress(address: Address): Long*/

}