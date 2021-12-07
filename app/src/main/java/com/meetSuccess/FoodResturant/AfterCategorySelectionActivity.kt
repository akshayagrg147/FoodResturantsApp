package com.meetSuccess.FoodResturant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
//import com.meetSuccess.Database.CartItems
//import com.meetSuccess.Database.ProductDatabase

import com.meetSuccess.FoodResturant.Adapter.ListItemsAfterCategorySelectionAdapter
import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.Util.ApiState
import com.meetSuccess.FoodResturant.ViewModel.MainViewModel
import com.meetSuccess.FoodResturant.databinding.ActivityListItemAfterCategorySelectionBinding
import com.rowland.cartcounter.view.CartCounterActionView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AfterCategorySelectionActivity :  AppCompatActivity() {
    private lateinit var binding: ActivityListItemAfterCategorySelectionBinding
    //lateinit var database: ProductDatabase

    private lateinit var categorySelectAdapter: ListItemsAfterCategorySelectionAdapter
    private  lateinit var actionView:CartCounterActionView



    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListItemAfterCategorySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  database= ProductDatabase.getContactDatabase(this@AfterCategorySelectionActivity)

       //  supportActionBar?.hide()


        initRecyclerview()
        //  initRecyclerviewMeals()


        mainViewModel.getPost()
        // mainViewModel.getLatestMeals()
        val parentjob= lifecycleScope.launchWhenStarted {

            val catergories=async { mainViewModel._postStateFlow.collect {it->
                when(it){
                    is ApiState.Loading->{
                        binding.recyclerCategory.isVisible=false
                        binding.shimmerCategoryListItems.shimmerCategory .isVisible= true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerCategory.isVisible = false
                        binding.shimmerCategoryListItems.shimmerCategory .isVisible= true
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.SuccessCategories->{
                        Log.d("dsfddd","dsfsd");
                        //  binding.shimmerCategory.shimmerCategory .isVisible= true


                        binding.recyclerCategory.isVisible = true
                        binding.shimmerCategoryListItems.shimmerCategory .isVisible= false
                        categorySelectAdapter.setData(it.data.categories)
                        categorySelectAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty->{

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
            R.id.action_addcart->{

                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val itemData = menu?.findItem(R.id.action_addcart)
         actionView = itemData?.actionView as CartCounterActionView
        actionView.setItemData(menu, itemData)
        actionView.setCount(2)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.cartmenu,menu)
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
        categorySelectAdapter= ListItemsAfterCategorySelectionAdapter(ArrayList(),object :ListItemsAfterCategorySelectionAdapter.onclick{
            override fun itemclicked(item: Categories.Category) {
//                if(database.contactDao().getCartItemsCounts()>0)
//                actionView.count=database.contactDao().getCartItemsCounts()+1
//                else
//                    actionView.count=5
                lifecycle.coroutineScope.launch{
                 //   database.contactDao().insertCartItem(CartItems(item.getIdCategory(),item.getStrCategory(),item.getStrCategoryDescription(), item.getStrCategoryThumb()))

                }



                Toast.makeText(applicationContext, "item should update", Toast.LENGTH_LONG).show()
            }


        })
        binding.recyclerCategory.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@AfterCategorySelectionActivity)
            adapter=categorySelectAdapter
        }
    }

}