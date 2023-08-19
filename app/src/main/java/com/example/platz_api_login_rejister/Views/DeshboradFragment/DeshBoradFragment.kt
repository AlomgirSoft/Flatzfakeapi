package com.example.platz_api_login_rejister.Views.DeshboradFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import coil.load

import com.example.platz_api_login_rejister.R
import com.example.platz_api_login_rejister.databinding.FragmentDeshBoradBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeshBoradFragment : Fragment(),ProductPagingAdapter.Lisener {
    lateinit var binding:FragmentDeshBoradBinding
    private lateinit var toggle: ActionBarDrawerToggle

    val viewModel:UserProfileViewModel by viewModels()



    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModelProduct:ProductViewModel by viewModels()

    lateinit var adapterPaging: ProductPagingAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding=FragmentDeshBoradBinding.inflate(inflater,container,false)





        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)



        adapterPaging=ProductPagingAdapter(this)
        binding.recyclerView.adapter = adapterPaging


       // viewModelProduct.getProduct()

        viewModelProduct.pagingData.observe(viewLifecycleOwner)
        {


            adapterPaging.submitData(lifecycle,it)
            Log.d("TG","TA${it}")

        }

         viewModel.userProfile()







        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toggle = ActionBarDrawerToggle(requireActivity(), binding.drawerlayout, binding.toolbar, R.string.open_drawer, R.string.close_drawer)
        binding.drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        viewModel.userProfileResponse.observe(viewLifecycleOwner){


            if (it.isSuccessful){
                it.body()?.let {

                    Log.d("TAG","PROFILE DATAA ${it.name}")


                    binding.apply {
                      //  this.profileName.text=it.name

                       val headerView = binding.navigationView.getHeaderView(0)


                       // Now you can find views inside the header layout and manipulate them as needed
                       var name = headerView.findViewById<TextView>(R.id.profileName)
                       var email = headerView.findViewById<TextView>(R.id.userEmail)
                       var image = headerView.findViewById<ImageView>(R.id.profileImage)
                       var imageBtn = headerView.findViewById<ImageView>(R.id.editBtn)

                       name.text = it.name
                       email.text = it.email
                        image.load(it.avatar)

                        imageBtn.setOnClickListener {_->
                            val userId=it.id
                            val bundle=Bundle()
                                bundle.putInt("id", userId!!)
                                    findNavController().navigate(R.id.action_deshBoradFragment_to_createProductFragment2,bundle)

                        }
                        image.setOnClickListener {

//                            val intent = Intent(activity, DeshBoradFragment::class.java)
//                            startActivity(intent)

                          //  findNavController().navigate(R.id.action_deshBoradFragment_to_imageUploadFragment)
                        }
                    }


                }
            }
        }






    }



    override fun productClickLisener(productId: Int) {
        val bundle=Bundle()
        bundle.putInt("id",productId)
        findNavController().navigate(R.id.action_deshBoradFragment_to_productDetailsFragment,bundle)
    }


}

















