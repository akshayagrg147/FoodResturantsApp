package com.meetSuccess.FoodResturant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetSuccess.FoodResturant.Adapter.CategoryAdapter
import com.meetSuccess.FoodResturant.Util.ApiState
import com.meetSuccess.FoodResturant.ViewModel.MainViewModel
import com.meetSuccess.FoodResturant.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var MealsAdapter: MealsAdapter


    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
      //  initRecyclerviewMeals()


       // mainViewModel.getPost()
        mainViewModel.getLatestMeals()
       val parentjob= lifecycleScope.launchWhenStarted {
          val mealsCategory= async { mainViewModel._postStateFlow.collect {it->
              when(it){
                  is ApiState.Loading->{
                      binding.viewPagerHeaderRecyclerview.isVisible=false
                      //  binding.progressBar.isVisible=true
                  }
                  is ApiState.Failure -> {
                      binding.viewPagerHeaderRecyclerview.isVisible = false
                      //  binding.shimmerCategory.shimmerCategory .isVisible= false
                      Log.d("main", "onCreate: ${it.msg}")
                  }
                  is ApiState.SuccessMeals->{
                      Toast.makeText(this@MainActivity,"success"+it.data.meals.size,Toast.LENGTH_LONG).show()
                      //  binding.shimmerCategory.shimmerCategory .isVisible= true


                      binding.viewPagerHeaderRecyclerview.isVisible = true
                      // binding.progressBar.isVisible = false
                      MealsAdapter.setData(it.data.meals)
                      MealsAdapter.notifyDataSetChanged()
                  }
                  is ApiState.Empty->{

                  }
              }
          } }
//            val catergories=async { mainViewModel._postStateFlow.collect {it->
//                when(it){
//                    is ApiState.Loading->{
//                        binding.recyclerCategory.isVisible=false
//                        //  binding.progressBar.isVisible=true
//                    }
//                    is ApiState.Failure -> {
//                        binding.recyclerCategory.isVisible = false
//                        //  binding.shimmerCategory.shimmerCategory .isVisible= false
//                        Log.d("main", "onCreate: ${it.msg}")
//                    }
//                    is ApiState.SuccessCategories->{
//                        //  binding.shimmerCategory.shimmerCategory .isVisible= true
//
//
//                        binding.recyclerCategory.isVisible = true
//                        // binding.progressBar.isVisible = false
//                        categoryAdapter.setData(it.data.categories)
//                        categoryAdapter.notifyDataSetChanged()
//                    }
//                    is ApiState.Empty->{
//
//                    }
//                }
//            } }
//           catergories.await()
           mealsCategory.await()
           print("both api get called")

        }
        parentjob.invokeOnCompletion { print("api call completion") }



    }

    private fun initRecyclerviewMeals() {
        MealsAdapter= MealsAdapter(ArrayList())
        binding.viewPagerHeaderRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager=GridLayoutManager(this@MainActivity,3)
            adapter=categoryAdapter
        }
    }

    private fun initRecyclerview() {
        categoryAdapter= CategoryAdapter(ArrayList())
        binding.recyclerCategory.apply {
            setHasFixedSize(true)
            layoutManager=GridLayoutManager(this@MainActivity,3)
            adapter=categoryAdapter
        }
    }
}