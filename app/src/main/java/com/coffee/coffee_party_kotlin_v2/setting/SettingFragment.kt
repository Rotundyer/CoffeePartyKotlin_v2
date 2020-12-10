package com.coffee.coffee_party_kotlin_v2.setting

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.OnItemClickListener
import com.coffee.coffee_party_kotlin_v2.metods.addOnItemClickListener
import com.coffee.coffee_party_kotlin_v2.metods.lists.TypesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.setting_fragment.*
import kotlinx.android.synthetic.main.types_dialog.view.*

class SettingFragment : Fragment() {

    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = arguments?.getString("title")
            setDisplayHomeAsUpEnabled(true)
        }

        types_dialog.setOnClickListener {
            val adapter = TypesAdapter(context)

            val dialogView = LayoutInflater.from(context).inflate(R.layout.types_dialog, null)
            val builder = context?.let { it -> AlertDialog.Builder(it).setView(dialogView) }
            val dialog: AlertDialog = builder!!.create()

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogView.types_list.layoutManager = LinearLayoutManager(activity)
            dialogView.types_list.setHasFixedSize(true)
            dialogView.types_list.adapter = adapter

            viewModel.getTypesList().observe(viewLifecycleOwner, Observer {
                it.let { adapter.refresh(it) }
            })

            dialogView.setOnClickListener {
                dialog.dismiss()
            }
        }
//        types_list.setHasFixedSize(true)
//        types_list.adapter = adapter

    }

    fun setTitle(i: String) {
        types_dialog.text = i
    }
}