package com.meetSuccess.FoodResturant




import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetSuccess.Database.CartItems
import com.meetSuccess.FoodResturant.databinding.CartitemsProductBinding


import com.meetSuccess.FoodResturant.databinding.ItemproductCategoryBinding


class CartItemssAdapter(private var categories1: List<CartItems>

)
    : RecyclerView.Adapter<CartItemssAdapter.PostViewHolder>() {

    private lateinit var binding:CartitemsProductBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = CartitemsProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
  binding.totalquantity.text=categories1.get(position).id.toString()


    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

  override fun getItemCount(): Int {
        Log.d("calllllllll",categories1.size.toString());
        return categories1.size

    }


}
