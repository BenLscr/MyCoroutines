package com.benlsc.mycoroutines.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.benlsc.mycoroutines.room.models.Agent

@Dao
interface AgentDao {

    @Query("SELECT * FROM Agent WHERE :id = agent_id")
    fun getAgent(id: Int): LiveData<Agent>

    @Insert
    suspend fun insertAgent(agent: Agent): Long

}