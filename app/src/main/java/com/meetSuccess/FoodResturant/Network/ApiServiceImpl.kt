package com.meetSuccess.FoodResturant.Network

import com.meetSuccess.FoodResturant.Model.Categories
import com.meetSuccess.FoodResturant.Model.Meals
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost():Categories = apiService.getCategories()
    suspend fun getLatestMeals():Meals = apiService.getMeal()
}