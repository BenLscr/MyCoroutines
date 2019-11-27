package com.benlsc.mycoroutines.room.repository

import androidx.lifecycle.LiveData
import com.benlsc.mycoroutines.room.dao.AgentDao
import com.benlsc.mycoroutines.room.models.Agent

class AgentDataRepository(private val agentDao: AgentDao) {

    fun getAgent(id: Int): LiveData<Agent> = agentDao.getAgent(id)

    suspend fun insertAgent(agent: Agent): Long = agentDao.insertAgent(agent)

}