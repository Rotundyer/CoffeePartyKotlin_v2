package com.coffee.coffee_party_kotlin_v2.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.lists.BaseAdapter
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "История"
            setDisplayHomeAsUpEnabled(true)
        }

        history_list.setHasFixedSize(true)
        viewModel.all.observe(viewLifecycleOwner, Observer { all ->
            Log.e("Adapter", all.toString())
            history_list.adapter = BaseAdapter(all, context)
        })
    }

}