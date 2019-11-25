package com.benlsc.mycoroutines

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TokenDao {

    @Query("SELECT * FROM Token")
    fun getAllTokens(): LiveData<List<Token>>

    @Query("SELECT * FROM TOKEN WHERE :id = id")
    fun getToken(id: Int): LiveData<Token>

    @Insert
    fun insertToken(token: Token)

    @Query("DELETE FROM Token WHERE :id = id")
    fun deleteToken(id: Int)

}