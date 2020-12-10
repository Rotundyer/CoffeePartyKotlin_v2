package com.coffee.coffee_party_kotlin_v2.setting

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.OnItemClickListener
import com.coffee.coffee_party_kotlin_v2.metods.addOnItemClickListener
import com.coffee.coffee_party_kotlin_v2.metods.api.Types
import com.coffee.coffee_party_kotlin_v2.metods.lists.TypesAdapter
import kotlinx.android.synthetic.main.setting_fragment.*
import kotlinx.android.synthetic.main.types_dialog.view.*

@Suppress("DEPRECATION", "NAME_SHADOWING")
class SettingFragment : Fragment() {

    private lateinit var viewModels: SettingViewModel
    private var types: List<Types.Result>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.setting_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModels = ViewModelProvider(this).get(SettingViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = arguments?.getString("title")
            setDisplayHomeAsUpEnabled(true)
        }

        types_dialog.setOnClickListener {

            val dialogView = LayoutInflater.from(context).inflate(R.layout.types_dialog, null)
            val builder = context?.let { it -> AlertDialog.Builder(it).setView(dialogView) }
            val dialog: AlertDialog = builder!!.create()

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogView.types_list.layoutManager = LinearLayoutManager(activity)
            dialogView.types_list.setHasFixedSize(true)

            viewModels.getTypesList().observe(viewLifecycleOwner, Observer {
                it.let {
                    types = it
                    val adapter = TypesAdapter(context, it)
                    dialogView.types_list.adapter = adapter
                }
            })

            dialogView.types_list.addOnItemClickListener(object : OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    types_dialog.text = types?.get(position)?.title.toString()
                    dialog.dismiss()
                }

            })

            dialogView.setOnClickListener {
                dialog.dismiss()
            }
        }

    }
}