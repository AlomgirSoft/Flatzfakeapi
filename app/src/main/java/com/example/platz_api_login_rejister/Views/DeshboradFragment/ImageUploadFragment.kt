package com.example.platz_api_login_rejister.Views.DeshboradFragment

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LOGGER
import coil.load
import com.example.platz_api_login_rejister.Models.createproduct.RequestCreateProduct
import com.example.platz_api_login_rejister.R

import com.example.platz_api_login_rejister.databinding.FragmentImageUploadBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.util.ArrayList
import java.util.UUID
import kotlin.math.log10

@AndroidEntryPoint
class ImageUploadFragment : Fragment() {
    lateinit var binding:FragmentImageUploadBinding
    lateinit var profileUri:Uri
    val viewModel : ProductViewModel by viewModels()
    var permissionGranted: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentImageUploadBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
  binding.productCreateBtn.setOnClickListener {



      permissionGranted?.let {

          if (it){
              uploadFile(profileUri)
              // findNavController().navigate(R.id.action_imageUploadFragment_to_deshBoradFragment)
          }else{
              Toast.makeText(requireContext(), "Permission Required!", Toast.LENGTH_SHORT)
                  .show()
          }

      }

          // createProductHandel(titel,description,price)

  }

        viewModel._responseProducteImage.observe(viewLifecycleOwner){
            if (it.isSuccessful){

                Log.d("TAGT","data${it.body()}")
                Toast.makeText(requireActivity(),"done",Toast.LENGTH_SHORT).show()
            }



        }


        binding.galaryImage.setOnClickListener {


            ImagePicker.with(this)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(512, 512)  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)


                }   
            //binding.button.visibility=view.visibility
        }




        viewModel._responseProducteImage.observe(viewLifecycleOwner){



        }

     viewModel.ResponseProductImage.observe(viewLifecycleOwner){
         if (it.isSuccessful){

          //  binding.galaryImageShow.load(it.body()!!.location)

             val titel = binding.edtitel.text.toString()
             val description = binding.edDescription.text.toString()
             val price = binding.edPrice.text.toString()

             val images= mutableListOf<String>( it.body()?.location!!)


             createProductHandel(titel,description,price, images)
             Toast.makeText(requireActivity(),"done",Toast.LENGTH_SHORT).show()
             Log.d("image","product${it.body()}")
         }
     }

    }

    private fun createProductHandel(titel: String, description: String, price: String, images: MutableList<String>) {


        val request = RequestCreateProduct(1,description = description,images=images, price = price, title = titel)

        viewModel.getcreateProductImage(request)
    }

    private fun uploadFile(uri: Uri) {


        val fileDir=requireActivity().filesDir
        val file = File(fileDir,"ima_profile${UUID.randomUUID()}.png")

        val inputStream=    requireActivity().contentResolver.openInputStream(uri)

        val outputStream =FileOutputStream(file)
        inputStream?.copyTo(outputStream)


        val  requestBody=file.asRequestBody("image/*".toMediaTypeOrNull())

        val part=MultipartBody.Part.createFormData("file",file.name,requestBody)



        viewModel.getProductImage(part)
        inputStream?.close()
        outputStream.close()

    }


    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!

                    profileUri = fileUri


                    binding.galaryImage.setImageURI(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {


                }
                else -> {


                }
            }
        }

    override fun onResume() {
        super.onResume()
        checkPermission()
    }

fun checkPermission() {

        Dexter.withContext(requireContext())
            .withPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    permissionGranted = report.areAllPermissionsGranted()

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {


                }
            }).check()

    }



}
