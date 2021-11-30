package com.meetSuccess.FoodResturant.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.R

import com.meetSuccess.FoodResturant.databinding.ItemRecyclerCategoryBinding
import com.squareup.picasso.Picasso


class CategoryAdapter(private var categories1: List<Categories.Category>)
    : RecyclerView.Adapter<CategoryAdapter.PostViewHolder>() {

    private lateinit var binding:ItemRecyclerCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = ItemRecyclerCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.categoryName.text=categories1.get(position).getStrCategory()

        Picasso.get().load(categories1.get(position).getStrCategoryThumb()).placeholder(R.drawable.ic_circle)
            .into(binding.categoryThumb)

    }

//    override fun getItemCount(): Int = categories1.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

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