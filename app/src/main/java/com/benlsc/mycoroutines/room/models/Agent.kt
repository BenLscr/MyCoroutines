package com.benlsc.mycoroutines.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Agent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "agent_id")
    var id: Int = 0,
    val name: String,
    val surname: String
)