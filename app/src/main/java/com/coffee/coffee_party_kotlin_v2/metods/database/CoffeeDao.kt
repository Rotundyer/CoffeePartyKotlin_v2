package com.coffee.coffee_party_kotlin_v2.metods.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffeeBase")
    fun getAll(): LiveData<List<CoffeeBase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coffeeBase: CoffeeBase)
}