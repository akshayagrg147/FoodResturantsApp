package com.meetSuccess.FoodResturant

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.meetSuccess.FoodResturant.Adapter.CategoryAdapter
import com.meetSuccess.FoodResturant.Adapter.ViewPagerHeaderAdapter
import com.meetSuccess.FoodResturant.Util.ApiState
import com.meetSuccess.FoodResturant.ViewModel.MainViewModel
import com.meetSuccess.FoodResturant.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var MealsAdapter:  ViewPagerHeaderAdapter


    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




       // supportActionBar?.hide()

        initRecyclerviewMeals()
        initRecyclerview()
      //  initRecyclerviewMeals()


        mainViewModel.getPost()
       // mainViewModel.getLatestMeals()
       val parentjob= lifecycleScope.launchWhenStarted {
          val mealsCategory= async { mainViewModel._postStateFlow.collect { it->
              when(it){
                  is ApiState.Loading -> {
                      binding.viewPagerHeaderRecyclerview.isVisible = false
                      binding.shimmerMeal.shimmerViewContainer2.isVisible = true
                  }
                  is ApiState.Failure -> {
                      binding.viewPagerHeaderRecyclerview.isVisible = false
                      binding.shimmerMeal.shimmerViewContainer2.isVisible = true
                      Log.d("main", "onCreate: ${it.msg}")
                  }
                  is ApiState.SuccessCategories -> {
                      //  binding.shimmerCategory.shimmerCategory .isVisible= true


                      binding.viewPagerHeaderRecyclerview.isVisible = true
                      binding.shimmerMeal.shimmerViewContainer2.isVisible = false
                      MealsAdapter.setData(it.data.categories)
                      categoryAdapter.notifyDataSetChanged()
                      binding.viewPagerHeaderRecyclerview.post(Runnable { // Call smooth scroll
                          binding.viewPagerHeaderRecyclerview.smoothScrollToPosition(it.data.categories.size - 1);
                      })


                  }
                  is ApiState.Empty -> {

                  }
              }
          } }
            val catergories=async { mainViewModel._postStateFlow.collect { it->
                when(it){
                    is ApiState.Loading -> {
                        binding.recyclerCategory.isVisible = false
                        binding.shimmerCategory.shimmerCategory.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerCategory.isVisible = false
                        binding.shimmerCategory.shimmerCategory.isVisible = true
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.SuccessCategories -> {
                        //  binding.shimmerCategory.shimmerCategory .isVisible= true


                        binding.recyclerCategory.isVisible = true
                        binding.shimmerCategory.shimmerCategory.isVisible = false
                        categoryAdapter.setData(it.data.categories)
                        categoryAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {

                    }
                }
            } }
           catergories.await()
           mealsCategory.await()
           print("both api get called")

        }
        parentjob.invokeOnCompletion { print("api call completion") }



    }
//
    private fun initRecyclerviewMeals() {
        MealsAdapter= ViewPagerHeaderAdapter(ArrayList(), this)
        binding.viewPagerHeaderRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL)
            adapter=MealsAdapter

        }
    }

    private fun initRecyclerview() {
        categoryAdapter= CategoryAdapter(ArrayList(), this)
        binding.recyclerCategory.apply {
            setHasFixedSize(true)
            layoutManager=GridLayoutManager(this@MainActivity, 3)
            adapter=categoryAdapter
        }
    }
}