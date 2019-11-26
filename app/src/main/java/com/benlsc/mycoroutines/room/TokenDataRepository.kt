package com.benlsc.mycoroutines.room

import androidx.lifecycle.LiveData
import com.benlsc.mycoroutines.room.Token
import com.benlsc.mycoroutines.room.TokenDao

class TokenDataRepository(
    private val tokenDao: TokenDao
) {

    fun getAllTokens(): LiveData<List<Token>> = tokenDao.getAllTokens()

    fun getToken(id: Int): LiveData<Token> = tokenDao.getToken(id)

    fun insertToken(token: Token) = tokenDao.insertToken(token)

    fun deleteToken(id: Int) = tokenDao.deleteToken(id)

}