package com.example.platz_api_login_rejister.Views.DeshboradFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.platz_api_login_rejister.Models.product.ResponseProductItem

import com.example.platz_api_login_rejister.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

val viewModel:ProductViewModel by viewModels ()


lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProductDetailsBinding.inflate(inflater,container,false)


        requireArguments().getInt("id").let {
            viewModel.getProductId(it)

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.productResponseId.observe(viewLifecycleOwner){

            if (it.isSuccessful){


            setProduct(it.body()!!)



            }

        }





    }

    private fun setProduct(it:ResponseProductItem){

        binding.priceTextView.text="price: ${it.price}"
        binding.descriptionTextView.text=it.description
        binding.titleTextView.text=it.title





        val images = it.images ?: emptyList()
        val imageViews = listOf(binding.image1, binding.image2,binding.image3)

        for (i in 0 until minOf(images.size, imageViews.size)) {
            val img_url = images[i]
            imageViews[i].load(img_url)
        }

        it.category?.let { ctg ->
    binding.categoryNameTextView.text = ctg.name
      binding.categoryImageView.load(ctg.image)

        }
    }
}