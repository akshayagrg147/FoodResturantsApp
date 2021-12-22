package com.meetSuccess.FoodResturant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.meetSuccess.Database.AddressItems
import com.meetSuccess.FoodResturant.databinding.SavedAddressBinding


class AddressItemssAdapter(
    private var categories1: List<AddressItems>,itemChossen1:AddressItemssAdapter.AddressChosen


)
    : RecyclerView.Adapter<AddressItemssAdapter.PostViewHolder>() {

    private lateinit var binding: SavedAddressBinding
    var selectedPosition:Int  = -1
     val itemChossen:AddressItemssAdapter.AddressChosen=itemChossen1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = SavedAddressBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        binding.nameSection.setText(
            categories1.get(position).customer_name.toString() + "\n" + categories1.get(
                position
            ).Address1.toString() + "," + categories1.get(position).Address2.toString() + "," + categories1.get(
                position
            ).PinCode.toString() + "," + categories1.get(position).customer_PhoneNumber.toString()
        )

        holder.itemView.findViewById<RadioButton>(R.id.radioButton1).setTag(position);
        holder.itemView.findViewById<RadioButton>(R.id.radioButton1).setOnClickListener(View.OnClickListener { v ->
            itemCheckChanged(v)
        })

    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun getItemCount(): Int {
        Log.d("calllllllll", categories1.size.toString());
        return categories1.size

    }
    private fun itemCheckChanged(v: View) {
        selectedPosition = v.getTag() as Int
        itemChossen.itemChossen(selectedPosition)
        //notifyDataSetChanged()
    }
interface AddressChosen{
    fun itemChossen( int: Int)
}

}

