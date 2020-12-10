package com.coffee.coffee_party_kotlin_v2.setting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.coffee.coffee_party_kotlin_v2.metods.api.Types
import com.coffee.coffee_party_kotlin_v2.metods.api.getTypes
import retrofit2.Call
import retrofit2.Response

class SettingRepository() {

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