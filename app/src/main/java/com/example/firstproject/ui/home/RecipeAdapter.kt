package com.example.firstproject.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.R
import com.example.firstproject.Recipe


class RecipeAdapter (
    private val recipeList: List<Recipe>
): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_recipe,parent,false)
        return  RecipeViewHolder(view)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.recipe_dif.text = recipeList[position].Difficulty.toString()
        holder.recipe_name.text = recipeList[position].Name
        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("name", recipeList[position].Name)
            bundle.putString("Calorie", recipeList[position].Calorie.toString())
            bundle.putString("Difficulty", recipeList[position].Difficulty.toString())
            bundle.putString("Time", recipeList[position].Time.toString())
            bundle.putString("Ingredients", recipeList[position].Ingredients)

            //Navigation.findNavController(it).navigate(R.id.selected, bundle)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recipe_name: TextView
        var recipe_dif: TextView


        init {
            recipe_name= itemView.findViewById(R.id.recipename)
            recipe_dif = itemView.findViewById(R.id.difficulty)
        }
    }
}