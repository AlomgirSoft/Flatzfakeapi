package com.example.platz_api_login_rejister.Views.DeshboradFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.platz_api_login_rejister.Models.product.ResponseProductItem
import com.example.platz_api_login_rejister.databinding.ItemproductBinding

class Adapter(private val lisener: Lisener) :ListAdapter<ResponseProductItem,Adapter.ProductViewModel> (COMPARATOR) {

       interface Lisener{
           fun productClickLisener(productId:Int)
       }

    class ProductViewModel(val binding:ItemproductBinding):RecyclerView.ViewHolder(binding.root)


    companion object{
        val COMPARATOR =object : DiffUtil.ItemCallback<ResponseProductItem>(){
            override fun areItemsTheSame(
                oldItem: ResponseProductItem,
                newItem: ResponseProductItem
            ): Boolean {
               return oldItem==newItem
            }

            override fun areContentsTheSame(
                oldItem: ResponseProductItem,
                newItem: ResponseProductItem
            ): Boolean {
             return oldItem.id==newItem.id
            }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewModel {
      return ProductViewModel(  ItemproductBinding.inflate(LayoutInflater.from(parent.context), parent, false),)
    }

    override fun onBindViewHolder(holder: ProductViewModel, position: Int) {
        getItem(position).let {

            holder.binding.priceTextView.text="Price: ${it.price}"
            holder.binding.descriptionTextView.text=it.description
            holder.binding.titleTextView.text=it.title


            it.images?.get(0)?.let { img_url ->
                holder.binding.image1.load(img_url)
            }

            it.images?.get(1)?.let { img_url ->
                holder.binding.image2.load(img_url)
            }

            it.images?.get(2)?.let { img_url ->
                holder.binding.image3.load(img_url)

            }

            it.category?.let { ctg ->
                holder.binding.categoryNameTextView.text = ctg.name
                holder.binding.categoryImageView.load(ctg.image)

            }

            holder.itemView.setOnClickListener{_->
          lisener.productClickLisener(it.id)

            }

        }

    }
}