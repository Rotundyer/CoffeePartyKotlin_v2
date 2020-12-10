package com.coffee.coffee_party_kotlin_v2.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coffee.coffee_party_kotlin_v2.metods.api.Coffee

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MenuRepository()

    private val URL: String = "http:/testapi.servertest.pro/api/"
    private val token = "Token d23d2a771294e285257976bc1e5a4040fa6c14c8"

    object number {
        var number = 0
    }

    fun getCount() = repository.getCount()

    fun getCoffeeList(i: Boolean, up: Boolean): MutableLiveData<List<Coffee.Result>> {
        if (i) number.number += 10
        return repository.getCoffee(URL, token, number.number, up)
    }

    fun numberUpdate() {
        number.number = 0
    }
}
