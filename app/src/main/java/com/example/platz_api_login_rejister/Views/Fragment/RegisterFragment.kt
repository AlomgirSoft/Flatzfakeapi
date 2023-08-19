package com.example.platz_api_login_rejister.Views.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.platz_api_login_rejister.Models.Register.RequestRegister
import com.example.platz_api_login_rejister.R
import com.example.platz_api_login_rejister.Views.Fragment.registerViewModel.RegisterViewModel

import com.example.platz_api_login_rejister.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding

    private val viewModel:RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentRegisterBinding.inflate(inflater,container,false)

        viewModel.responseRegister.observe(viewLifecycleOwner){

            if (it.isSuccessful){

                Log.d("TAG","DATA${it.body()}")
                Toast.makeText(activity,"done", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)


            }

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
  binding.singUpBtn.setOnClickListener {
      val name=binding.edName.text.toString().trim()
      val email=binding.edEmail.text.toString().trim()
      val password=binding.edPassword.text.toString().trim()

      handelRegister(name,email,password)
  }



    }

    private fun handelRegister(name: String, email: String, password: String) {


        val  register=RequestRegister(name = name, email = email, password = password, avatar = "https://scontent.fdac31-1.fna.fbcdn.net/v/t39.30808-6/249402420_242171877971994_6468805352450089088_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=8bfeb9&_nc_eui2=AeG-pSVhKk7S2_WUWShbE5eSMrjFmltk804yuMWaW2TzTkpplaLsvjQUbHc1ZjhGIPbRiu3Kh9W6WnOjRxmzisPK&_nc_ohc=EatCeTEJjpUAX9ZBPe4&_nc_ht=scontent.fdac31-1.fna&oh=00_AfA6QCxNtiKR0ScKHl8HtJI2LWZmhcDwfCG7CXMsgv0W8g&oe=64BDE95B")
  viewModel.register(register)
    }

}