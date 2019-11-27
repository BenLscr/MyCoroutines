package com.benlsc.mycoroutines.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Address(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "address_id")
    var id: Int = 0,
    val postalCode: Int
)