package com.benlsc.mycoroutines

import androidx.lifecycle.LiveData

class TokenDataRepository(
    private val tokenDao: TokenDao
) {

    fun getAllTokens(): LiveData<List<Token>> = tokenDao.getAllTokens()

    fun getToken(id: Int): LiveData<Token> = tokenDao.getToken(id)

    fun insertToken(token: Token) = tokenDao.insertToken(token)

    fun deleteToken(id: Int) = tokenDao.deleteToken(id)

}