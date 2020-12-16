package com.coffee.coffee_party_kotlin_v2.getting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.database.AppDatabase
import com.coffee.coffee_party_kotlin_v2.metods.database.CoffeeBase
import com.coffee.coffee_party_kotlin_v2.metods.database.CoffeeDao
import kotlinx.android.synthetic.main.getting_fragment.*

class GettingFragment : Fragment() {

    private lateinit var viewModel: GettingViewModel
    lateinit var navController: NavController
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.getting_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GettingViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Выдача напитка"
            setDisplayHomeAsUpEnabled(true)
        }

        when (resources.configuration.orientation) {
            1 -> getting_image.layoutParams = LinearLayout.LayoutParams(600, 600)
            else -> getting_image.layoutParams = LinearLayout.LayoutParams(160, 160)
        }

        getting_title.text = arguments?.getString("title")
        getting_size.text = "Размер: " + arguments?.getString("size")
        getting_sugar.text = arguments?.getString("sugar") + " кубиков сахара"

        context?.let {
            Glide
                .with(it)
                .load(
                    when (arguments?.getString("image")) {
                        null -> R.mipmap.coffee_image
                        else -> arguments?.getString("image")
                    }
                )
                .into(getting_image)
        }
        getting_next.setOnClickListener {
            Thread {
                database = context.let { AppDatabase.getInstance(it) }
                val dao: CoffeeDao = database.dao()
                val into = CoffeeBase(
                    arguments?.getString("title")!!,
                    when(arguments?.getString("image")) {
                        null -> "null"
                        else -> arguments?.getString("image")
                    },
                    arguments?.getString("size")!!,
                    arguments?.getString("sugar")!!.toInt()
                )
                dao.insert(into)
            }.start()

            navController = view.let { Navigation.findNavController(it!!) }
            navController.navigate(R.id.menuFragment)
        }
    }

}