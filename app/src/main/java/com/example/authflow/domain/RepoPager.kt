package com.example.authflow.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.authflow.domain.model.Products
import com.example.authflow.presentation.nested_recycler.MyDataPagingSource
import kotlinx.coroutines.flow.Flow

class RepoPager {

    fun getProduct(): Flow<PagingData<Products>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MyDataPagingSource() }
        ).flow
    }


}