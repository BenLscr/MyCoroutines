package com.benlsc.mycoroutines.room.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.benlsc.mycoroutines.room.models.Address
import com.benlsc.mycoroutines.room.models.Agent

@Entity(foreignKeys = [ForeignKey(entity = Address::class, parentColumns = ["address_id"], childColumns = ["addressId"]),
    ForeignKey(entity = Agent::class, parentColumns = ["agent_id"], childColumns = ["agentId"])])
class Property(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val description: String,
    val addressId: Long,
    val agentId: Long
) {
    @Embedded
    var address: Address? = null
    @Embedded
    var agent: Agent? = null
}