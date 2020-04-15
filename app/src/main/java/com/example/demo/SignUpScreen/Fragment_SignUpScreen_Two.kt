package com.example.demo.SignUpScreen

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.example.demo.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment__sign_up_screen__two.*
import kotlinx.android.synthetic.main.fragment__sign_up_screen__two.view.*
import java.text.SimpleDateFormat
import java.util.*

class Fragment_SignUpScreen_Two : Fragment() {

    // Declaring variable
    var familyCountTrack = 0

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment__sign_up_screen__two, container, false)

        //Increment Family Count
        view.btnIncremenetFamilyCount.setOnClickListener {
            familyCountTrack++
            txtFamilyCountValue.text = familyCountTrack.toString()
        }

        //Decrement Family Count
        view.btnDecrementFamilyCount.setOnClickListener {
            familyCountTrack--
            if (familyCountTrack == 0) {
                txtFamilyCountValue.text= "0"
            }
            txtFamilyCountValue.text= familyCountTrack.toString()
        }

        //Opening Calender and Setting Date for TextField Birthdate
        val myCalendar = java.util.Calendar.getInstance()
        val datePickerListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                myCalendar.set(java.util.Calendar.YEAR, year)
                myCalendar.set(java.util.Calendar.MONTH, monthOfYear)
                myCalendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd/MMM/yyyy" //In which you need put here
                val sdf = SimpleDateFormat(myFormat, Locale.UK)
                txtBirthDate.setText(sdf.format(myCalendar.time))
            }
        view.txtBirthDate.setOnClickListener {

            DatePickerDialog(view.context, datePickerListener, myCalendar
                .get(java.util.Calendar.YEAR), myCalendar.get(java.util.Calendar.MONTH),
                myCalendar.get(java.util.Calendar.DAY_OF_MONTH)).show()
        }

        //Opening Gallery and Setting image to imageview
        view.editProfilePicture.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(view.context,android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                    //permission denied
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }

        view.radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = view.findViewById(checkedId)
                (activity as SignUp).gender = "${radio.text}"
            })

        return view
    }


    override fun onResume() {
        super.onResume()
        val parentActivity = activity as SignUp
        parentActivity.trackFragment = 2
        parentActivity.btnNextSignUpFragment.text = "Next"
        parentActivity.txtfragmentTrack.text = "Step 2 of 3"
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000
        //Permission code
        private val PERMISSION_CODE = 1001
        //Getting selected image path
        var imgPath:Uri? = null
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
                    Toast.makeText(view?.context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imgProfile_Picture_SignUpScreen.setImageURI(data?.data)
            imgPath = data?.data
        }
    }
}