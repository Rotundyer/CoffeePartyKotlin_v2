package com.coffee.coffee_party_kotlin_v2.metods.lists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.api.Types
import com.coffee.coffee_party_kotlin_v2.setting.SettingViewModel
import kotlinx.android.synthetic.main.types_billet.view.*

class TypesAdapter(private var context: Context?, private var types: List<Types.Result>) :
    RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

    var viewModel = SettingViewModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.types_billet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypesAdapter.ViewHolder, position: Int) {
        holder.bind(types[position])
    }

    override fun getItemCount() = types.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(types: Types.Result) = with(itemView) {
            types_button_dialog.text = types.title
        }
    }
}