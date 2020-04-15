package com.example.demo.HomeScreen.Profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.SignUpScreen.SignUp
import kotlinx.android.synthetic.main.fragment_profile_screen.*
import kotlinx.android.synthetic.main.profile_header_list_.*
import java.util.ArrayList

class ProfileScreen : Fragment(), AdapterOnCLick {

    // declaring variables
    private var profileList = ArrayList<ProfileScreenDataClass>()
    private var headerList = ArrayList<profileScreenHeaderClass>()
    private lateinit var adapter: ProfileScreenAdapterClass

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileScreenRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = ProfileScreenAdapterClass( this,profileList,headerList )
        profileScreenRecyclerView.adapter = adapter

        userData()
        headerData()

        }


    //passing row data
    private fun userData() {
        var data: ProfileScreenDataClass
        val userdata = SignUp.signUpDetails

        // if user not looged in
        if (userdata.isEmpty()) {
            val dummyDetails = arrayOf("FirstName","LastName","UserName","EmailId","Gender","BirthDate","About Yourself","Self Knwledge Point")
            for (i in 0..(dummyDetails.size - 1)) {
                data = ProfileScreenDataClass(
                    dummyDetails[i]
                )
                profileList.add(data)
            }
        }
        // if user logged in
        else {
            for (i in 0..(userdata.size - 1)) {
                data = ProfileScreenDataClass(
                    userdata[i]
                )
                profileList.add(data)
            }
        }

       }

    // passing header data
    private fun headerData() {
        val data: profileScreenHeaderClass
        if (SignUp.headerDetails.size != 0) {
            val headerProfilePicture = SignUp.headerDetails[1] as Uri?
            val headerUserName = SignUp.headerDetails[0] as String
            data = profileScreenHeaderClass(headerProfilePicture,headerUserName)
            headerList.add(data)
        } else {
            val headerProfilePicture = null
            val headerUserName = "UserName"
            data = profileScreenHeaderClass(headerProfilePicture,headerUserName)
            headerList.add(data)
        }

    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 100
        //Permission code
        private val PERMISSION_CODE = 101
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imgUserProfilePicture.setImageURI(data?.data)
        }
    }

    override fun onClick() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) ==
                    PackageManager.PERMISSION_DENIED) {
                    //permission denied
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery()
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
    }

}
