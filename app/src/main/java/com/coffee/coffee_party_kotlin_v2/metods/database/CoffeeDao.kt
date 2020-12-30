package com.coffee.coffee_party_kotlin_v2.metods.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffeeBase")
    fun getAll(): LiveData<List<CoffeeBase>>

    @Query("SELECT * FROM coffeeBase ORDER BY id DESC LIMIT 1")
    fun getId(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coffeeBase: CoffeeBase)
}