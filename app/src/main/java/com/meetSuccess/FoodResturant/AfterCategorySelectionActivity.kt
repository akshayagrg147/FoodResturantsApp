package com.meetSuccess.FoodResturant

//import com.meetSuccess.Database.CartItems
//import com.meetSuccess.Database.ProductDatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.meetSuccess.Database.CartItems
import com.meetSuccess.Database.ProductDatabase
import com.meetSuccess.FoodResturant.Adapter.ListItemsAfterCategorySelectionAdapter
import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.Util.ApiState
import com.meetSuccess.FoodResturant.ViewModel.MainViewModel
import com.meetSuccess.FoodResturant.databinding.ActivityListItemAfterCategorySelectionBinding
import com.rowland.cartcounter.view.CartCounterActionView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class AfterCategorySelectionActivity :  AppCompatActivity() {
    private lateinit var binding: ActivityListItemAfterCategorySelectionBinding
    lateinit var database: ProductDatabase

    private lateinit var categorySelectAdapter: ListItemsAfterCategorySelectionAdapter
    private  lateinit var actionView:CartCounterActionView



    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListItemAfterCategorySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= ProductDatabase.getInstance(this@AfterCategorySelectionActivity)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)


        initRecyclerview()
        //  initRecyclerviewMeals()


        mainViewModel.getPost()
        // mainViewModel.getLatestMeals()
        val parentjob= lifecycleScope.launchWhenStarted {

            val catergories=async { mainViewModel._postStateFlow.collect { it->
                when(it){
                    is ApiState.Loading -> {
                        binding.recyclerCategory.isVisible = false
                        binding.shimmerCategoryListItems.shimmerCategory.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerCategory.isVisible = false
                        binding.shimmerCategoryListItems.shimmerCategory.isVisible = true
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.SuccessCategories -> {
                        Log.d("dsfddd", "dsfsd");
                        //  binding.shimmerCategory.shimmerCategory .isVisible= true


                        binding.recyclerCategory.isVisible = true
                        binding.shimmerCategoryListItems.shimmerCategory.isVisible = false
                        categorySelectAdapter.setData(it.data.categories)
                        categorySelectAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {

                    }
                }
            } }
            catergories.await()

            print("one api get called")

        }
        parentjob.invokeOnCompletion { print("api call completion") }



    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //return super.onOptionsItemSelected(item)
        return when(item.itemId){
            R.id.action_addcart -> {
                val intent = Intent(this,CartItemss::class.java);
                this.startActivity(intent);



                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val itemData = menu?.findItem(R.id.action_addcart)
         actionView = itemData?.actionView as CartCounterActionView
        actionView.setItemData(menu, itemData)
        database.contactDao().getTotalProductItems().observe(this@AfterCategorySelectionActivity, {
            actionView.count = it.toString().toIntOrNull()!!
        })
//        actionView.setCount(0)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.cartmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    //
//    private fun initRecyclerviewMeals() {
//        MealsAdapter= MealsAdapter(ArrayList())
//        binding.viewPagerHeaderRecyclerview.apply {
//            setHasFixedSize(true)
//            layoutManager=GridLayoutManager(this@MainActivity, 3)
//            adapter=categoryAdapter
//        }
//    }

    private  fun initRecyclerview() {
        categorySelectAdapter= ListItemsAfterCategorySelectionAdapter(baseContext, ArrayList(),
            object : ListItemsAfterCategorySelectionAdapter.onclick {
                override fun itemclicked(item: Categories.Category) {


                    lifecycle.coroutineScope.launch {
                        // database.contactDao().getProductBasedId(1212).observe(this@AfterCategorySelectionActivity,{})
                        val intger: Int = database.contactDao().getProductBasedIdCount("121212")
                        database.contactDao()
                            .insertCartItem(CartItems("121212", item.getStrCategoryThumb(), intger + 1, 12, "dddd"))
//                        Log.d("countis",database.contactDao().getProductBasedIdCount("121212").toString())


                    }
                    database.contactDao().getTotalProductItems()
                        .observe(this@AfterCategorySelectionActivity, {
                            actionView.count = it.toString().toIntOrNull()!!
                        })
                    val dialog = BottomSheetDialog(this@AfterCategorySelectionActivity)

                    val inflater = LayoutInflater.from(this@AfterCategorySelectionActivity)
                    val view = inflater.inflate(R.layout.bottom_sheet_dialog, null)
                    view.findViewById<TextView>(R.id.idTVCourseName).setText(item.getStrCategory())
                    view.findViewById<Button>(R.id.idBtnProceed).setOnClickListener {
                        database.contactDao().getAllAddress()
                            .observe(this@AfterCategorySelectionActivity, {
                                if ((it != null) && (it.size > 0)) {
                                    val intent = Intent(
                                        this@AfterCategorySelectionActivity,
                                        ProceedToAddress::class.java
                                    );
                                    this@AfterCategorySelectionActivity.startActivity(intent);

                                } else {
                                    val intent = Intent(
                                        this@AfterCategorySelectionActivity,
                                        AddNewAddressActivity::class.java
                                    );
                                    this@AfterCategorySelectionActivity.startActivity(intent);
                                }
                            })
                    }
                        //  dialog.setCancelable(false)
                        dialog.setContentView(view)
                        dialog.show()



                }


                })
        binding.recyclerCategory.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@AfterCategorySelectionActivity)
            adapter=categorySelectAdapter
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}