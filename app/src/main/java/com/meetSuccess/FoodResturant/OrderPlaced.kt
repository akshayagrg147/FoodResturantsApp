package com.meetSuccess.FoodResturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.meetSuccess.FoodResturant.databinding.ActivityListItemAfterCategorySelectionBinding
import com.meetSuccess.FoodResturant.databinding.ActivityOrderPlacedBinding

class OrderPlaced : AppCompatActivity() {
    private lateinit var binding: ActivityOrderPlacedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_placed)
        binding= ActivityOrderPlacedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        binding.backToHomeBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java);
            this.startActivity(intent);
        }
    }
}