package com.example.platz_api_login_rejister.Views.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.platz_api_login_rejister.Constens.KAY_ACCESS_TOKEN
import com.example.platz_api_login_rejister.Constens.KAY_REFRESH_TOKEN
import com.example.platz_api_login_rejister.Models.Login.RequestLogin
import com.example.platz_api_login_rejister.R
import com.example.platz_api_login_rejister.Utils.PrefManeger
import com.example.platz_api_login_rejister.Views.Fragment.LoginViewModel.LoginViewModel

import com.example.platz_api_login_rejister.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

  private val viewModel: LoginViewModel by viewModels()
    @Inject
  lateinit var prefManeger: PrefManeger

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater,container,false)

        viewModel.responseLogin.observe(viewLifecycleOwner){

            if (it.isSuccessful){
                         prefManeger.setPref(KAY_ACCESS_TOKEN,it.body()?.accessToken!!)
                         prefManeger.setPref(KAY_REFRESH_TOKEN,it.body()?.refreshToken!!)
                Toast.makeText(activity,"done",Toast.LENGTH_LONG).show()

//                val intent = Intent(requireContext(), DrawerDeshActivity::class.java)
//                startActivity(intent)
                findNavController().navigate(R.id.action_loginFragment_to_deshBoradFragment)
            }


        }
        binding.rejistretionBtn.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
          val email=binding.edEmail.text.toString().trim()
          val password=binding.edPassword.text.toString().trim()

          handelLogin(email,password)


        }




    }

    private fun handelLogin(email: String, password: String) {

        val  request=RequestLogin(email=email, password = password)

        viewModel.login(request)



    }


}