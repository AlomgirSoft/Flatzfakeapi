package com.example.platz_api_login_rejister.di

import com.example.platz_api_login_rejister.Constens.BASE_URI
import com.example.platz_api_login_rejister.Serviecs.AuthServiec
import com.example.platz_api_login_rejister.Serviecs.ProductServiec
import com.example.platz_api_login_rejister.Serviecs.UserServiec
import com.example.platz_api_login_rejister.Utils.AuthInterseftor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
    fun retrofitProvides():Retrofit.Builder{

        return Retrofit.Builder().baseUrl(BASE_URI).addConverterFactory(GsonConverterFactory.create())


    }

    @Provides
    @Singleton
    fun authServiec(retrofit: Retrofit.Builder):AuthServiec{
        return retrofit.build().create(AuthServiec::class.java)
    }


    @Provides
    @Singleton
    fun providesHttpClient(interceptor: AuthInterseftor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }



    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit.Builder, client: OkHttpClient): UserServiec {
        return retrofit.client(client).build().create(UserServiec::class.java)

    }

    @Provides
    @Singleton
    fun providesProductService(retrofit: Retrofit.Builder): ProductServiec {
        return retrofit.build().create(ProductServiec::class.java)

    }



}