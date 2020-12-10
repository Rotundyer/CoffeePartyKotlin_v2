package com.coffee.coffee_party_kotlin_v2.metods.lists

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.api.Types
import com.coffee.coffee_party_kotlin_v2.setting.SettingFragment
import kotlinx.android.synthetic.main.setting_fragment.view.*
import kotlinx.android.synthetic.main.types_billet.view.*

class TypesAdapter(private var context: Context?) :
    RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

    private var types: List<Types.Result> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.types_billet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypesAdapter.ViewHolder, position: Int) {
        holder.bind(types[position])

        holder.itemView.setOnClickListener {
            Log.e("JOJO", SettingFragment().view?.types_dialog?.text.toString())
            Log.e("JOJO", "JOJO")
            SettingFragment().setTitle(types[position].title)
        }
    }

    override fun getItemCount() = types.size

    fun refresh(type: List<Types.Result>) {
        Log.e("Types", type.toString())
        this.types = type
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(types: Types.Result) = with(itemView) {
            types_button_dialog.text = types.title
        }
    }
}