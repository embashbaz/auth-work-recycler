package com.example.authflow.presentation.nested_recycler

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.authflow.data.AuthFlowRepository
import com.example.authflow.domain.RepoPager
import com.example.authflow.domain.model.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NestedRecyclerViewModel @Inject constructor(private val repo: AuthFlowRepository) : ViewModel(){
    //\val pagingDataFlow: Flow<PagingData<Products>>
    fun getProducts(): Flow<PagingData<Products>>{

        Log.d("PAGED DATA VIEWMODEL: ", repo.getProduct().toString())

        return repo.getProduct()
    }






}