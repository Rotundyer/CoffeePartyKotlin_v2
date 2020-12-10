package com.coffee.coffee_party_kotlin_v2.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffee.coffee_party_kotlin_v2.metods.api.Coffee
import com.coffee.coffee_party_kotlin_v2.metods.api.getList
import retrofit2.Call
import retrofit2.Response

class MenuRepository() {

    object count{
        var count = 0
    }

    fun getCount(): Int{
        return count.count
    }

    fun getCoffee(URL: String, token: String, number: Int, up: Boolean): MutableLiveData<List<Coffee.Result>> {
        val data = MutableLiveData<List<Coffee.Result>>()
        getList(URL, token, MenuViewModel.number.number).enqueue(object :
            retrofit2.Callback<Coffee> {
            override fun onResponse(call: Call<Coffee>, response: Response<Coffee>) {
                data.value = response.body()!!.results
                count.count = response.body()!!.count
            }

            override fun onFailure(call: Call<Coffee>, t: Throwable) {
                Log.e("Error: ", t.message)
            }
        })
        return data
    }
}