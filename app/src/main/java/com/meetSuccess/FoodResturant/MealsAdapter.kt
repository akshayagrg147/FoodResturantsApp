package com.meetSuccess.FoodResturant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.Model.Meals
import com.meetSuccess.FoodResturant.databinding.EachRowBinding

class MealsAdapter(private var categories1: List<Meals.Meal>)
    : RecyclerView.Adapter<MealsAdapter.PostViewHolder>() {

    private lateinit var binding: EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.tasks.text=categories1.get(position).getstrMeal()
    }

//    override fun getItemCount(): Int = categories1.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(categoriesList: List<Meals.Meal>)
    {
        categories1=categoriesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categories1.size

    }


}