package com.coffee.coffee_party_kotlin_v2.metods.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 2, entities = [CoffeeBase::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): CoffeeDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context?): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context!!, AppDatabase::class.java, "coffeeBase")
                    .fallbackToDestructiveMigration().build()
            }
            return INSTANCE as AppDatabase
        }
    }
}