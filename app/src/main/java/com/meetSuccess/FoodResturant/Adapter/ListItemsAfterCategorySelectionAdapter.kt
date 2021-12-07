package com.meetSuccess.FoodResturant.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetSuccess.FoodResturant.Model.Categories


import com.meetSuccess.FoodResturant.databinding.ItemproductCategoryBinding
import com.squareup.picasso.Picasso


class ListItemsAfterCategorySelectionAdapter(private var categories1: List<Categories.Category>,
                                             onitemClicked1: ListItemsAfterCategorySelectionAdapter.onclick
)
    : RecyclerView.Adapter<ListItemsAfterCategorySelectionAdapter.PostViewHolder>() {

    private lateinit var binding:ItemproductCategoryBinding
    private  var onitemClicked:ListItemsAfterCategorySelectionAdapter.onclick=onitemClicked1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = ItemproductCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        holder.itemView.setOnClickListener{
//            Log.d("callingTest","ddddddd--item"+position)
//
//
//        }



        binding.AddButton.setOnClickListener{
//            Log.d("callingTest","ddddddd--button"+position)
//binding.ItemAdded.visibility=View.GONE
//            binding.AddButton.visibility=View.GONE
            Log.d("callingTest","ddddddd--button"+position)
            onitemClicked.itemclicked(categories1.get(position))
        }
//        binding.categoryName.text=categories1.get(position).getStrCategory()
//
//        Picasso.get().load(categories1.get(position).getStrCategoryThumb()).placeholder(R.drawable.ic_circle)
//            .into(binding.categoryThumb)

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
      Log.d("calllllllll",categories1.size.toString());
        return categories1.size

    }
    interface onclick{
        public fun itemclicked(item:Categories.Category)
    }

}