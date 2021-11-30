package com.meetSuccess.FoodResturant.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetSuccess.FoodResturant.Model.Categories

import com.meetSuccess.FoodResturant.databinding.EachRowBinding

class CategoryAdapter(private var categories1: List<Categories.Category>)
    : RecyclerView.Adapter<CategoryAdapter.PostViewHolder>() {

    private lateinit var binding:EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.tasks.text=categories1.get(position).getStrCategory()
    }

//    override fun getItemCount(): Int = categories1.size

    class PostViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(categoriesList: List<Categories.Category>)
    {
        categories1=categoriesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categories1.size

    }

}