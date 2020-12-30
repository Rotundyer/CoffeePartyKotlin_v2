package com.coffee.coffee_party_kotlin_v2.setting

import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.databinding.SettingFragmentBinding
import com.coffee.coffee_party_kotlin_v2.metods.OnItemClickListener
import com.coffee.coffee_party_kotlin_v2.metods.Phrases
import com.coffee.coffee_party_kotlin_v2.metods.addOnItemClickListener
import com.coffee.coffee_party_kotlin_v2.metods.api.Types
import com.coffee.coffee_party_kotlin_v2.metods.lists.TypesAdapter
import kotlinx.android.synthetic.main.setting_fragment.*
import kotlinx.android.synthetic.main.sugar_dialog.view.*
import kotlinx.android.synthetic.main.types_dialog.view.*

@Suppress("DEPRECATION", "NAME_SHADOWING")
class SettingFragment : Fragment() {

    private lateinit var viewModels: SettingViewModel
    private var types: List<Types.Result>? = null
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModels = ViewModelProvider(this).get(SettingViewModel::class.java)
        val binding: SettingFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.setting_fragment, container, false)
        return binding.apply {
            this.viewModel = viewModels
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = arguments?.getString("title")
            setDisplayHomeAsUpEnabled(true)
        }

        setting_next.setOnClickListener {
            if (
                Phrases().toastSetting(
                    context,
                    types_dialog.text.toString(),
                    sugar_dialog.text.toString()
                )
            ) {
                navController = view.let { Navigation.findNavController(it!!) }
                navController.navigate(
                    R.id.gettingFragment,
                    bundleOf(
                        "title" to arguments?.getString("title"),
                        "image" to arguments?.getString("image"),
                        "size" to types_dialog.text.toString(),
                        "sugar" to sugar_dialog.text.toString()
                    )
                )
            }
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

        sugar_dialog.setOnClickListener {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.sugar_dialog, null)
            val builder = context?.let { it -> AlertDialog.Builder(it).setView(dialogView) }
            val dialog: AlertDialog = builder!!.create()

            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            sugar_dialog.text = "0"

            val seekbar = dialogView.seekbar
            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    dialogView.seekbar_text.text =
                        seekbar.progress.toString() + Phrases().dialogSetting(seekbar.progress)
                    sugar_dialog.text = seekbar.progress.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

            dialogView.setOnClickListener {
                dialog.dismiss()
            }
        }

    }
}