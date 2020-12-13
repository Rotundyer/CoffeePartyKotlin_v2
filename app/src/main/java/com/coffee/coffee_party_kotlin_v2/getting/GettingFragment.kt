package com.coffee.coffee_party_kotlin_v2.getting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coffee.coffee_party_kotlin_v2.R

class GettingFragment : Fragment() {

    companion object {
        fun newInstance() = GettingFragment()
    }

    private lateinit var viewModel: GettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.getting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GettingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}