package com.example.platz_api_login_rejister.Paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.platz_api_login_rejister.Models.product.ResponseProductItem
import com.example.platz_api_login_rejister.Serviecs.ProductServiec
import javax.inject.Inject

class ProductPagingSource  @Inject constructor( private val serviec: ProductServiec): PagingSource<Int,ResponseProductItem>() {



    override fun getRefreshKey(state: PagingState<Int, ResponseProductItem>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val  anchorPage= state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseProductItem> {

        val page=params.key ?: 0

        Log.d("TAG", "page: $page ")
        Log.d("TAG", "offset: ${params.loadSize * page} ")
        Log.d("TAG", "limit: ${params.loadSize} ")

        val response = serviec.getAllPagingProduct(params.loadSize * page, params.loadSize)


        return  try {
            LoadResult.Page(
                data = response,
                prevKey = if ( page == 0 ) null else page - 1,
                nextKey = if ( response.isEmpty() ) null else page +1

            )




        }catch (e:Exception){
            LoadResult.Error(e)
        }



    }


}