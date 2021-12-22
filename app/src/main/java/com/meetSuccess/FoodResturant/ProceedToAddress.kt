package com.meetSuccess.FoodResturant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetSuccess.Database.AddressItems
import com.meetSuccess.Database.ProductDatabase
import com.meetSuccess.FoodResturant.databinding.ActivityCartItemssBinding
import com.meetSuccess.FoodResturant.databinding.ActivityProceedToAddressBinding

class ProceedToAddress :  AppCompatActivity() ,AddressItemssAdapter.AddressChosen{
    private lateinit var binding: ActivityProceedToAddressBinding
    lateinit var database: ProductDatabase
    lateinit var ListAddress: List<AddressItems>

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

        binding.addnewaddress.setOnClickListener {
            val intent = Intent(this,AddNewAddressActivity::class.java);
            this.startActivity(intent);
        }
        binding.confirmorder.setOnClickListener{
            val intent = Intent(this,OrderPlaced::class.java);
            this.startActivity(intent);
            finish()

        }
        // initRecyclerview()


        database.contactDao().getAllAddress().observe(this@ProceedToAddress,{
            ListAddress=it
            categorySelectAdapter= AddressItemssAdapter(it,this)

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

    override fun itemChossen(int: Int) {
       Toast.makeText(this@ProceedToAddress,ListAddress.get(int).Address1,Toast.LENGTH_SHORT).show()
    }


}