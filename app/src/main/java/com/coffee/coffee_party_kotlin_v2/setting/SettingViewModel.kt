package com.coffee.coffee_party_kotlin_v2.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coffee.coffee_party_kotlin_v2.metods.api.Types

class SettingViewModel : ViewModel() {

    private val repository = SettingRepository()

    private val URL: String = "http:/testapi.servertest.pro/api/"
    private val token = "Token d23d2a771294e285257976bc1e5a4040fa6c14c8"

    fun getTypesList(): MutableLiveData<List<Types.Result>> {
        return repository.getType(URL, token)
    }
}