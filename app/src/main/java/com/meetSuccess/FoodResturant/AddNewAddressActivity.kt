package com.meetSuccess.FoodResturant

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.meetSuccess.Database.AddressItems
import com.meetSuccess.Database.ProductDatabase
import com.meetSuccess.FoodResturant.databinding.ActivityAddNewAddressBinding
import kotlinx.coroutines.launch

class AddNewAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewAddressBinding
    lateinit var database: ProductDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddNewAddressBinding.inflate(layoutInflater)
        database= ProductDatabase.getInstance(this@AddNewAddressActivity)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        setContentView(binding.root)
        binding.addressUpdateButton.setOnClickListener {
            lifecycle.coroutineScope.launch {
                if((binding.nameEditText.getText()
                        .isEmpty()))
                {
                    Toast.makeText(
                        this@AddNewAddressActivity,
                        "Name should not be null",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
               else if((binding.phonenoEditText.getText()
                        .isEmpty()))
                {
                    Toast.makeText(
                        this@AddNewAddressActivity,
                        "address1 should not be null",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
                else  if((binding.pincodeEditText.getText()
                        .isEmpty()))
                {
                    Toast.makeText(
                        this@AddNewAddressActivity,
                        "address2 should not be null",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
                else  if((binding.address1EditText.getText()
                        .isEmpty()))
                {
                    Toast.makeText(
                        this@AddNewAddressActivity,
                        "LandMark should not be null",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
                else  if((binding.address2EditText.getText()
                        .isEmpty()))
                {
                    Toast.makeText(
                        this@AddNewAddressActivity,
                        "Phone number should not be null",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
                else  if((binding.lanMark.getText()
                        .isEmpty()))
                {
                    Toast.makeText(
                        this@AddNewAddressActivity,
                        "Pincode should not be null",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                }
                else
                {

                    database.contactDao().insertAddressItem(
                        AddressItems(
                            binding.nameEditText.getText().toString(),
                            binding.phonenoEditText.getText().toString(),
                            Integer.parseInt(binding.pincodeEditText.getText().toString()),
                            binding.address1EditText.text.toString(),
                            binding.address2EditText.text.toString(),
                            binding.lanMark.text.toString()
                        )
                    )
                    Toast.makeText(
                        this@AddNewAddressActivity,
                        "Address Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@AddNewAddressActivity, ProceedToAddress::class.java);
                    this@AddNewAddressActivity.startActivity(intent);
//

                }


            }

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}