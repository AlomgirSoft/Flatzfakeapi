package com.example.platz_api_login_rejister.Models.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.platz_api_login_rejister.Models.createproduct.RequestCreateProduct
import com.example.platz_api_login_rejister.Models.createproduct.ResponseCreateProduct
import com.example.platz_api_login_rejister.Models.file.ResponseImage
import com.example.platz_api_login_rejister.Models.product.ResponseProduct
import com.example.platz_api_login_rejister.Models.product.ResponseProductItem
import com.example.platz_api_login_rejister.Models.update.ResponseUpdate
import com.example.platz_api_login_rejister.Models.userProfile.ResponseUserProfile
import com.example.platz_api_login_rejister.Paging.ProductPagingSource
import com.example.platz_api_login_rejister.Serviecs.AuthServiec
import com.example.platz_api_login_rejister.Serviecs.ProductServiec
import com.example.platz_api_login_rejister.Serviecs.UserServiec
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Part
import javax.inject.Inject


class ProductRepo  @Inject constructor(var productService: ProductServiec) {


   suspend fun getProduct():Response<ResponseProduct>{
        return productService.getProduct()
    }



      // product singal activity details
    suspend fun getProductId(id:Int):Response<ResponseProductItem>{
        return productService.getProductId(id)
    }
     // image upload
    suspend fun getProductImage(part: MultipartBody.Part):Response<ResponseImage>{
        return productService.getProductImage(part)
    }
      // create product
    suspend fun createProduct(requestCreateProduct: RequestCreateProduct):Response<ResponseCreateProduct>{
        return productService.createProduct(requestCreateProduct)
    }

// product update
    suspend fun productUpdate(id: Int):Response<ResponseUpdate>{
        return productService.updateProductId(id)
    }


  fun getAllPagingProduct() = Pager(
    config = PagingConfig(pageSize = 10, initialLoadSize = 10),
    pagingSourceFactory = {
        ProductPagingSource(productService)}
  ).liveData

}