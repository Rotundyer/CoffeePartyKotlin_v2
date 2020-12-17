package com.coffee.coffee_party_kotlin_v2.metods

import android.content.Context
import android.widget.Toast
import kotlinx.android.synthetic.main.setting_fragment.*

class Phrases {
    fun toastSetting(context: Context?, i: String, j: String): Boolean {
        if ((i == "Кликните") && (j == "Кликните")) {
            Toast.makeText(
                context,
                "Вы не выбрали размер напитка и количество сахара",
                Toast.LENGTH_LONG
            ).show()
            return false
        } else if ((i != "Кликните") && (j == "Кликните")) {
            Toast.makeText(
                context,
                "Вы не выбрали количество сахара",
                Toast.LENGTH_LONG
            ).show()
            return false
        } else if ((i == "Кликните") && (j != "Кликните")) {
            Toast.makeText(
                context,
                "Вы не выбрали размер напитка",
                Toast.LENGTH_LONG
            ).show()
            return false
        } else {
            return true
        }
    }
    fun dialogSetting(i: Int): String {
        return when(i){
            1 -> " кубик сараха"
            2, 3, 4 -> " кубика сахара"
            else -> " кубиков сахара"
        }
    }
}