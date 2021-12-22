package com.meetSuccess.FoodResturant




import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.meetSuccess.Database.CartItems
import com.meetSuccess.Database.ProductDatabase
import com.meetSuccess.FoodResturant.databinding.CartitemsProductBinding


class CartItemssAdapter(private var categories1: List<CartItems>,cartItemClickListnerr:cartItemClickListner,database1: ProductDatabase

)
    : RecyclerView.Adapter<CartItemssAdapter.PostViewHolder>() {

    private lateinit var binding:CartitemsProductBinding
    private  val database: ProductDatabase=database1

    private  val itemclickListner:cartItemClickListner=cartItemClickListnerr


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = CartitemsProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var totalNumber:Int= database.contactDao().getProductBasedIdCount(categories1.get(position).ProductIdNumber)
  binding.totalquantity.text=totalNumber.toString()
        binding.plusButton.findViewById<AppCompatButton>(R.id.plusButton).setOnClickListener{
//            totalNumber=totalNumber+1
//            binding.totalquantity.text=totalNumber.toString()
            itemclickListner.ClickedPlusButton(categories1.get(position))
        }
        binding.minusButton.findViewById<AppCompatButton>(R.id.minusButton).setOnClickListener{
//            totalNumber=totalNumber-1
//            binding.totalquantity.text=totalNumber.toString()
            itemclickListner.ClickedMinusButton(categories1.get(position))

        }

    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

  override fun getItemCount(): Int {
        Log.d("calllllllll",categories1.size.toString());
        return categories1.size

    }
    public interface cartItemClickListner{
        fun  ClickedPlusButton(cartitems: CartItems)
        fun  ClickedMinusButton(cartitems: CartItems)

    }


}
