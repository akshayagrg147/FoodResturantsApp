package com.meetSuccess.FoodResturant

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetSuccess.Database.CartItems
import com.meetSuccess.Database.ProductDatabase
import com.meetSuccess.FoodResturant.Adapter.ListItemsAfterCategorySelectionAdapter
import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.databinding.ActivityCartItemssBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
@AndroidEntryPoint
class CartItemss :  AppCompatActivity() {
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
           binding.totalquantity.text=it.toString()
        })
        database.contactDao().getTotalPrice().observe(this@CartItemss,{
            if(it!=null)
            binding.priceAmount.text="₹"+it.toString()

        })


                database.contactDao().getContact().observe(this@CartItemss,{
                    categorySelectAdapter= CartItemssAdapter(it)

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








}