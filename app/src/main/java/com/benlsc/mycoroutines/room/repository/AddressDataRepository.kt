package com.benlsc.mycoroutines.room.repository

import androidx.lifecycle.LiveData
import com.benlsc.mycoroutines.room.dao.AddressDao
import com.benlsc.mycoroutines.room.models.Address

class AddressDataRepository(private val addressDao: AddressDao) {

    /**
     * COROUTINE VERSION
     */

    suspend fun getAddress(id: Int): Address = addressDao.getAddress(id)

    suspend fun insertAddress(address: Address): Long = addressDao.insertAddress(address)

    /**
     * CLASSIC VERSION (LiveData & Executor)
     */

    /*fun getAddress(id: Int): LiveData<Address> = addressDao.getAddress(id)

    fun insertAddress(address: Address): Long = addressDao.insertAddress(address)*/

}