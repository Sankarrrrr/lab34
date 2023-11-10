package com.example.firstproject.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bekawestberg.loopinglayout.library.LoopingLayoutManager
import com.example.firstproject.R
import com.example.firstproject.Recipe
import com.example.firstproject.adapter.RecipeAdapter
import com.example.firstproject.databinding.FragmentHomeBinding
import com.example.laba2.api.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        lateinit var list: List<Recipe>
        println("Tut")
        Common.retrofitService.getRecipes().enqueue(object :
            Callback<List<Recipe>> {
            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                println("egr")
            }

            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                println("Успех ${response.body()}")
                list = response.body()!!
                list.forEach{
                    Log.e("", "Название ${it.Name}\n" +
                            "Каллорийность ${it.Calorie}\n" +
                            "Время приготовления: ${it.Time} \n" +
                            "Сложность ${it.Difficulty}\n" +
                            "Ингредиенты: ${it.Ingredients}")
                }
                val adapter = RecipeAdapter(list)
                view.findViewById<RecyclerView>(R.id.recyclerView).layoutManager = GridLayoutManager(requireContext(), 2)
                view!!.findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
            }
        })
        return view
    }


}