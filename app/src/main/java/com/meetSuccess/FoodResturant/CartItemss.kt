package com.meetSuccess.FoodResturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetSuccess.Database.CartItems
import com.meetSuccess.Database.ProductDatabase
import com.meetSuccess.FoodResturant.databinding.ActivityCartItemssBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartItemss :  AppCompatActivity(),CartItemssAdapter.cartItemClickListner {
    private lateinit var binding: ActivityCartItemssBinding
    lateinit var database: ProductDatabase
    private lateinit var categorySelectAdapter: CartItemssAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCartItemssBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= ProductDatabase.getInstance(this@CartItemss)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.replyTextview.setOnClickListener {

            database.contactDao().getAllAddress().observe(this@CartItemss,{
                if((it!=null)&&(it.size>0))
                    {
                        val intent = Intent(this,ProceedToAddress::class.java);
                        this.startActivity(intent);

                    }
                else
                {
                    val intent = Intent(this,AddNewAddressActivity::class.java);
                    this.startActivity(intent);
                }

            }
            )
        }
        // initRecyclerview()
        database.contactDao().getTotalProductItems().observe(this@CartItemss,{
           binding.totalquantity.setText(it.toString())
            if( it==0) {
                binding.emptyLayout.visibility = View.VISIBLE
                binding.linearlayout.visibility=View.GONE
                binding.linearLayoutButton.visibility=View.GONE

            }
        })
        database.contactDao().getTotalPrice().observe(this@CartItemss,{
            if(it!=null)
            binding.priceAmount.setText("₹"+it.toString())

        })


                database.contactDao().getContact().observe(this@CartItemss,{
                    categorySelectAdapter= CartItemssAdapter(it,this,database)

                    binding.recyclerCategory.isVisible = true
                  //  binding.shimmerCategoryListItems.shimmerCategory.isVisible = false

                    binding.recyclerCategory.apply {
                        setHasFixedSize(true)
                        layoutManager= LinearLayoutManager(this@CartItemss)
                        adapter=categorySelectAdapter
                    }
                })




    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun ClickedPlusButton(cartitems: CartItems) {
        lifecycle.coroutineScope.launch {
            // database.contactDao().getProductBasedId(1212).observe(this@AfterCategorySelectionActivity,{})

            val intger: Int = database.contactDao().getProductBasedIdCount(cartitems.ProductIdNumber)
            database.contactDao()
                .insertCartItem(CartItems(cartitems.ProductIdNumber, cartitems.strCategoryThumb, intger + 1, 12, "dddd"))
                       Log.d("coundddtis",database.contactDao().getProductBasedIdCount(cartitems.ProductIdNumber).toString())


        }

    }


}