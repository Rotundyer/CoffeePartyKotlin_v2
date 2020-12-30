package com.coffee.coffee_party_kotlin_v2.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coffee.coffee_party_kotlin_v2.metods.api.Types
import com.coffee.coffee_party_kotlin_v2.metods.repositories.apiRepository

class SettingViewModel : ViewModel() {
    val size = "Размер кружки"
    val button = "?"
    val sugar = "Сахар"
    val next = " Далее"

    private val repository = apiRepository()

    private val URL: String = "http:/testapi.servertest.pro/api/"
    private val token = "Token d23d2a771294e285257976bc1e5a4040fa6c14c8"

    fun getTypesList(): MutableLiveData<List<Types.Result>> {
        return repository.getType(URL, token)
    }
}