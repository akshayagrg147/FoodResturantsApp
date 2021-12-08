package com.meetSuccess.FoodResturant.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meetSuccess.FoodResturant.Repository.MainRepository
import com.meetSuccess.FoodResturant.Util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val postStateFlow:MutableStateFlow<ApiState>
    = MutableStateFlow(ApiState.Empty)

    val _postStateFlow:StateFlow<ApiState> = postStateFlow

    //suspend function always called with courtines

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepository.getPost()
            .catch { e->
               postStateFlow.value=ApiState.Failure(e)
            }.collect { data->
                postStateFlow.value=ApiState.SuccessCategories(data)
            }
    }
    fun getLatestMeals() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepository.getLatestMeals()
            .catch { e->
                postStateFlow.value=ApiState.Failure(e)
            }.collect { data->
                postStateFlow.value=ApiState.SuccessMeals(data)
            }
    }

}
