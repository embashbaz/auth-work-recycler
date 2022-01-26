package com.example.authflow.presentation.nested_recycler

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.authflow.domain.model.Products
import com.example.authflow.generateProducts
import com.example.authflow.getPage
import com.example.authflow.getPages
import java.io.IOException


class MyDataPagingSource(

): PagingSource<Int, Products>()  {
    private val START_PAGE = 1

    override fun getRefreshKey(state: PagingState<Int, Products>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Products> {
        val position = params.key ?: START_PAGE

        return try {
            val response = getPage(generateProducts(), position, params.loadSize)

            LoadResult.Page(
                data = response!!,
                prevKey = if (position == START_PAGE) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }

    }
}

