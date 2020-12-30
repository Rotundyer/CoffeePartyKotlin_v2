package com.coffee.coffee_party_kotlin_v2.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.coffee.coffee_party_kotlin_v2.metods.database.AppDatabase
import com.coffee.coffee_party_kotlin_v2.metods.database.CoffeeBase
import com.coffee.coffee_party_kotlin_v2.metods.repositories.bdRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = AppDatabase.getInstance(application)
    internal val all: LiveData<List<CoffeeBase>> = bdRepository(application).getBD()
}