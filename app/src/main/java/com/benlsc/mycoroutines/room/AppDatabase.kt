package com.benlsc.mycoroutines.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.benlsc.mycoroutines.room.dao.AddressDao
import com.benlsc.mycoroutines.room.dao.AgentDao
import com.benlsc.mycoroutines.room.dao.PropertyDao
import com.benlsc.mycoroutines.room.models.Address
import com.benlsc.mycoroutines.room.models.Agent
import com.benlsc.mycoroutines.room.models.Property

@Database(entities = [Address::class, Agent::class, Property::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
    abstract fun agentDao(): AgentDao
    abstract fun propertyDao(): PropertyDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            INSTANCE.let {  }
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "Database.db")
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}