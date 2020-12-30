package com.coffee.coffee_party_kotlin_v2.metods.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.coffee.coffee_party_kotlin_v2.menu.MenuViewModel
import com.coffee.coffee_party_kotlin_v2.metods.api.Coffee
import com.coffee.coffee_party_kotlin_v2.metods.api.Types
import com.coffee.coffee_party_kotlin_v2.metods.api.getList
import com.coffee.coffee_party_kotlin_v2.metods.api.getTypes
import retrofit2.Call
import retrofit2.Response

class apiRepository() {

    object countRepository{
        var count = 0
    }

    fun getCount(): Int{
        return countRepository.count
    }

    fun getCoffee(URL: String, token: String, number: Int, up: Boolean): MutableLiveData<List<Coffee.Result>> {
        val data = MutableLiveData<List<Coffee.Result>>()
        getList(URL, token, MenuViewModel.countViewModel.count).enqueue(object :
            retrofit2.Callback<Coffee> {
            override fun onResponse(call: Call<Coffee>, response: Response<Coffee>) {
                data.value = response.body()!!.results
                countRepository.count = response.body()!!.count
            }

            override fun onFailure(call: Call<Coffee>, t: Throwable) {
                Log.e("Error: ", t.message)
            }
        })
        return data
    }

    fun getType(URL: String, token: String): MutableLiveData<List<Types.Result>> {
        val data = MutableLiveData<List<Types.Result>>()
        getTypes(URL, token).enqueue(object : retrofit2.Callback<Types> {
            override fun onResponse(call: Call<Types>, response: Response<Types>) {
                data.value = response.body()!!.results
            }

            override fun onFailure(call: Call<Types>, t: Throwable) {
                Log.e("Error: ", t.message)
            }

        })
        return data
    }
}