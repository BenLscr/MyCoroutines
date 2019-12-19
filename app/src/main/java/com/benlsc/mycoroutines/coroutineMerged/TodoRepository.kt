package com.benlsc.mycoroutines.coroutineMerged

class TodoRepository {
    private var client: Webservice = Webservice.webservice

    suspend fun getTodo(id: Int) = client.getTodo(id)
}