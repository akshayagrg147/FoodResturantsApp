package com.meetSuccess.FoodResturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetSuccess.Database.ProductDatabase
import com.meetSuccess.FoodResturant.databinding.ActivityCartItemssBinding
import com.meetSuccess.FoodResturant.databinding.ActivityProceedToAddressBinding

class ProceedToAddress :  AppCompatActivity() {
    private lateinit var binding: ActivityProceedToAddressBinding
    lateinit var database: ProductDatabase
    private lateinit var categorySelectAdapter: AddressItemssAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProceedToAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        database= ProductDatabase.getInstance(this@ProceedToAddress)


        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        binding.replyTextview.setOnClickListener {
//            val intent = Intent(this,AddNewAddressActivity::class.java);
//            this.startActivity(intent);
        }
        // initRecyclerview()


        database.contactDao().getAllAddress().observe(this@ProceedToAddress,{
            categorySelectAdapter= AddressItemssAdapter(it)

            binding.recyclerCategory.isVisible = true
            //  binding.shimmerCategoryListItems.shimmerCategory.isVisible = false

            binding.recyclerCategory.apply {
                setHasFixedSize(true)
                layoutManager= LinearLayoutManager(this@ProceedToAddress)
                adapter=categorySelectAdapter
            }
        })




    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }








}