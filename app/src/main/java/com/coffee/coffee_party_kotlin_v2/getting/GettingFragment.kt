package com.coffee.coffee_party_kotlin_v2.getting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.coffee.coffee_party_kotlin_v2.R
import com.coffee.coffee_party_kotlin_v2.metods.database.AppDatabase
import com.coffee.coffee_party_kotlin_v2.metods.repositories.bdRepository
import kotlinx.android.synthetic.main.getting_fragment.*

class GettingFragment : Fragment() {

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
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Выдача напитка"
            setDisplayHomeAsUpEnabled(true)
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
                .apply(
                    when (resources.configuration.orientation) {
                        1 -> RequestOptions().override(600, 600)
                        else -> RequestOptions().override(160, 160)
                    }
                )
                .into(getting_image)
        }
        getting_next.setOnClickListener {
            Thread {
                bdRepository(context).insertBD(
                    arguments?.getString("title")!!,
                    when (arguments?.getString("image")) {
                        null -> "null"
                        else -> arguments?.getString("image")
                    }!!,
                    arguments?.getString("size")!!,
                    arguments?.getString("sugar")!!.toInt()
                )
            }.start()

            navController = view.let { Navigation.findNavController(it!!) }
            navController.navigate(R.id.menuFragment)
        }
    }

}