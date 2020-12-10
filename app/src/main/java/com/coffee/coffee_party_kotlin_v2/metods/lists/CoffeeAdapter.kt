package com.coffee.coffee_party_kotlin_v2.metods.lists

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.api.Coffee
import kotlinx.android.synthetic.main.coffee_billet.view.*
import kotlinx.android.synthetic.main.coffee_dialog.view.*


class CoffeeAdapter(private var context: Context?) :
    RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {

    lateinit var navController: NavController
    private var coffee: List<Coffee.Result> = ArrayList()
    private var coffee2: List<Coffee.Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeAdapter.ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.coffee_billet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeeAdapter.ViewHolder, position: Int) {
        holder.bind(coffee[position])

        holder.itemView.setOnClickListener { view ->
            navController = view.let { Navigation.findNavController(it) }
            navController.navigate(
                R.id.settingFragment,
                bundleOf(
                    "title" to coffee[position].title,
                    "image" to coffee[position].image
                )
            )
        }
    }

    override fun getItemCount() = coffee.size

    fun refresh(coffee: List<Coffee.Result>) {
        Log.e("Adapter", coffee.toString())
        if (coffee != this.coffee2) {
            this.coffee = this.coffee + coffee
            this.coffee2 = coffee
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(coffee: Coffee.Result) = with(itemView) {
            coffee_title.text = coffee.title
            Glide
                .with(context)
                .load(
                    when (coffee.image) {
                        null -> R.mipmap.coffee_image
                        else -> coffee.image
                    }
                )
                .into(findViewById(R.id.image_view))

            coffee_i.setOnClickListener {
                val dialogView = LayoutInflater.from(context).inflate(R.layout.coffee_dialog, null)

                val builder = AlertDialog.Builder(context).setView(dialogView)

                val dialog: AlertDialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                Glide
                    .with(context)
                    .load(
                        when (coffee.image) {
                            null -> R.mipmap.coffee_image
                            else -> coffee.image
                        }
                    )
                    .into(dialogView.dialog_view)

                dialogView.dialog_title.text = coffee.title
                dialogView.dialog_description.text = coffee.description

                dialogView.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }

}