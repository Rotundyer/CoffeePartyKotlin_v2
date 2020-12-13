package com.coffee.coffee_party_kotlin_v2.metods.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffeeBase")
class CoffeeBase(
    @PrimaryKey @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "size") var size: String,
    @ColumnInfo(name = "sugar") var sugar: Int
)