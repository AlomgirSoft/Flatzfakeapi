package com.example.platz_api_login_rejister.Views.DeshboradFragment




import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.platz_api_login_rejister.Models.updateuser.RequestUserUpdate
import com.example.platz_api_login_rejister.base.BaseFragment


import com.example.platz_api_login_rejister.databinding.FragmentImageUploadBinding
import com.example.platz_api_login_rejister.databinding.UserUpdateFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserUpdateFragment : BaseFragment<UserUpdateFragmentBinding>(UserUpdateFragmentBinding::inflate) {
          val viewModel: UserProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.responseUserUpdate.observe(viewLifecycleOwner){

      if (it.isSuccessful){
          Log.d("TAGT","response${it.body()}")


          Toast.makeText(requireContext(),"done",Toast.LENGTH_LONG).show()
      }



        }




        binding.updateBtn.setOnClickListener {
            Toast.makeText(requireContext(),"click",Toast.LENGTH_LONG).show()
       val name=binding.edName.text.toString()
       val email=binding.edEmail.text.toString()
            val request=RequestUserUpdate(email,name)
           viewModel.userUpdate(request)
        }





    }



}