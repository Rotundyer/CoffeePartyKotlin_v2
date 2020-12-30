package com.coffee.coffee_party_kotlin_v2.menu

import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.lists.CoffeeAdapter
import kotlinx.android.synthetic.main.menu_fragment.*


class MenuFragment : Fragment() {

    private var timeControl: Boolean = true
    private lateinit var viewModel: MenuViewModel
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.menu_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "CoffeeParty"
            setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        recycler_list.layoutManager = GridLayoutManager(
            context,
            when (resources.configuration.orientation) {
                1 -> 2
                else -> 3
            },
            LinearLayoutManager.VERTICAL,
            false
        )
        val adapter = CoffeeAdapter(context)
        recycler_list.setHasFixedSize(true)
        recycler_list.adapter = adapter

        viewModel.getCoffeeList(i = false, up = true).observe(viewLifecycleOwner, Observer {
            it.let { adapter.refresh(it) }
        })

        val layoutManager =
            LinearLayoutManager::class.java.cast(recycler_list.layoutManager)

        recycler_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val totalItemCount = layoutManager!!.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()

                if ((lastVisible + 1 == totalItemCount) && (totalItemCount < viewModel.getCount()) && timeControl) {
                    viewModel.getCoffeeList(i = true, up = false)
                        .observe(viewLifecycleOwner, Observer {
                            it.let { adapter.refresh(it) }
                        })
                    Timer()
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun onDestroyView() {
        val viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        viewModel.numberUpdate()
        super.onDestroyView()
    }

    fun Timer() {
        timeControl = false
        val timer = object : CountDownTimer(100, 100) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                timeControl = true
            }
        }
        timer.start()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_history -> {
                navController = view.let { Navigation.findNavController(it!!) }
                navController.navigate(R.id.historyFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
