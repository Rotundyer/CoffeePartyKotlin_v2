package com.coffee.coffee_party_kotlin_v2.metods.lists

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.database.CoffeeBase
import kotlinx.android.synthetic.main.coffee_dialog.view.*
import kotlinx.android.synthetic.main.history_billet.view.*

class BaseAdapter(var list: List<CoffeeBase>?, var context: Context?) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.history_billet, parent, false)
        Log.e("Adapter", list.toString())
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseAdapter.ViewHolder, position: Int) {
        holder.bindBind(list?.get(position))
    }

    override fun getItemCount() = list?.size ?: 0

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindBind(coffeeBase: CoffeeBase?) = with(itemView) {
            Log.e("DATA BASE", coffeeBase?.title.toString())
            itemView.history_title.text = coffeeBase?.title
            itemView.history_size.text = coffeeBase?.size
            itemView.history_sugar.text = coffeeBase?.sugar.toString()

            Glide
                .with(context)
                .load(
                    when (coffeeBase?.image) {
                        "null" -> R.mipmap.coffee_image
                        else -> coffeeBase?.image
                    }
                )
                .into(itemView.history_image)
        }
    }
}