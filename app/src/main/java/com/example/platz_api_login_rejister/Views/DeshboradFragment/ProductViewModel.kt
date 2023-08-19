package com.example.platz_api_login_rejister.Views.DeshboradFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.platz_api_login_rejister.Models.createproduct.RequestCreateProduct
import com.example.platz_api_login_rejister.Models.createproduct.ResponseCreateProduct
import com.example.platz_api_login_rejister.Models.file.ResponseImage
import com.example.platz_api_login_rejister.Models.product.ResponseProduct
import com.example.platz_api_login_rejister.Models.product.ResponseProductItem
import com.example.platz_api_login_rejister.Models.repositories.ProductRepo
import com.example.platz_api_login_rejister.Models.update.ResponseUpdate

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(var repo:ProductRepo):ViewModel() {
    private val response=MutableLiveData<Response<ResponseProduct>>()
    val productResponse:LiveData<Response<ResponseProduct>> = response

    fun getProduct(){
        viewModelScope.launch{
        var data=    repo.getProduct()
            response.postValue(data)
        }

    }
         // create single value
    private val _response=MutableLiveData<Response<ResponseProductItem>>()
    val productResponseId:LiveData<Response<ResponseProductItem>> = _response

    fun getProductId(id:Int){
        viewModelScope.launch{
            val data=    repo.getProductId(id)
            _response.postValue(data)
        }

    }

//image upload
    private val responseProductImage=MutableLiveData<Response<ResponseImage>>()
    val ResponseProductImage:LiveData<Response<ResponseImage>> = responseProductImage

    fun getProductImage(pair: MultipartBody.Part){
        viewModelScope.launch{
            val data=    repo.getProductImage(pair)
            responseProductImage.postValue(data)
        }

    }
// image file get product

    private val responseCreateProduct=MutableLiveData<Response<ResponseCreateProduct>>()
    val _responseProducteImage:LiveData<Response<ResponseCreateProduct>> = responseCreateProduct

    fun getcreateProductImage(requestCreateProduct: RequestCreateProduct){
        viewModelScope.launch{
            val data=    repo.createProduct(requestCreateProduct)
            responseCreateProduct.postValue(data)
        }

    }

// update product
    private val productUpdateResponse=MutableLiveData<Response<ResponseUpdate>>()
    val productUpdateResponseId:LiveData<Response<ResponseUpdate>> = productUpdateResponse

    fun productUpdate(id:Int){
        viewModelScope.launch{
            val data=    repo.productUpdate(id)
            productUpdateResponse.postValue(data)
        }

    }



    val pagingData=repo.getAllPagingProduct().cachedIn(viewModelScope)



}