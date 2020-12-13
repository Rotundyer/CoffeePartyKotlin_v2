package com.coffee.coffee_party_kotlin_v2.metods.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffeeBase")
    fun getAll(): LiveData<List<CoffeeBase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coffeeBase: CoffeeBase)
}