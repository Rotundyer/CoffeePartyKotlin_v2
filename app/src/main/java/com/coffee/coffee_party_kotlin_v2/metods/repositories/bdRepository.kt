package com.coffee.coffee_party_kotlin_v2.metods.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.coffee.coffee_party_kotlin_v2.metods.database.AppDatabase
import com.coffee.coffee_party_kotlin_v2.metods.database.CoffeeBase
import com.coffee.coffee_party_kotlin_v2.metods.database.CoffeeDao

class bdRepository(context: Context?) {
    var database = context.let { AppDatabase.getInstance(it) }
    val dao: CoffeeDao = database.dao()

    fun insertBD(title: String, image: String, size: String, sugar: Int
    ) {
        val into = CoffeeBase(
            when (dao.getId()) {
                null -> 0
                else -> dao.getId() + 1
            },
            title,
            image,
            size,
            sugar
        )
        dao.insert(into)
    }

    fun getBD(): LiveData<List<CoffeeBase>> = dao.getAll()
}