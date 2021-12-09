package com.meetSuccess.FoodResturant.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.meetSuccess.FoodResturant.AfterCategorySelectionActivity
import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.Model.Meals
import com.meetSuccess.FoodResturant.R
import com.meetSuccess.FoodResturant.databinding.ItemRecyclerCategoryBinding
import com.meetSuccess.FoodResturant.databinding.ItemViewPagerHeaderBinding
import com.squareup.picasso.Picasso

class ViewPagerHeaderAdapter(private var categories1: List<Categories.Category>, val context: Context)
    : RecyclerView.Adapter<ViewPagerHeaderAdapter.PostViewHolder>() {

    private lateinit var binding: ItemViewPagerHeaderBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = ItemViewPagerHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.mealName.text=categories1.get(position).getStrCategory()


        Picasso.get().load(categories1.get(position).getStrCategoryThumb()).placeholder(R.drawable.ic_circle)
            .into(binding.mealThumb)
        holder.itemView.setOnClickListener{



            val intent = Intent(context, AfterCategorySelectionActivity::class.java);

            context.startActivity(intent);

        }

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