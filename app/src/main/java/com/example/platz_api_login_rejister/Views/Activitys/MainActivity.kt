package com.example.platz_api_login_rejister.Views.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.platz_api_login_rejister.R
import com.example.platz_api_login_rejister.Views.DeshboradFragment.ProductPagingAdapter
import com.example.platz_api_login_rejister.Views.DeshboradFragment.ProductViewModel
import com.example.platz_api_login_rejister.Views.DeshboradFragment.UserProfileViewModel
import com.example.platz_api_login_rejister.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    val viewModel: UserProfileViewModel by viewModels()


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView



    private val viewModelProduct: ProductViewModel by viewModels()

    lateinit var adapterPaging: ProductPagingAdapter

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    }


