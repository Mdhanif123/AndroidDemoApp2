package com.example.demo.SignUpScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demo.OTP_Screen
import com.example.demo.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment__sign_up_screen__two.*
import kotlinx.android.synthetic.main.fragment__sign_up_screen_one.*
import kotlinx.android.synthetic.main.fragment__sign_up_screen_three.*
import java.lang.reflect.Array

class SignUp : AppCompatActivity() {

    //Declaring variables
    val manager = supportFragmentManager
    var trackFragment = 1
    var gender = "Empty"
    companion object {
        var signUpDetails = ArrayList<String>()
        var headerDetails = ArrayList<Any>()
    }

    // On create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val transactionForSignUpScreenOne = manager.beginTransaction()
        var fragmentForSignUpScreenOne =
            Fragment_SignUpScreenOne()
        transactionForSignUpScreenOne.replace(R.id.fragment_holder, fragmentForSignUpScreenOne)
        transactionForSignUpScreenOne.addToBackStack("fragmentForSignUpScreenOne")
        transactionForSignUpScreenOne.commit()

        // on click of next button
        btnNextSignUpFragment.setOnClickListener {

            // Data validation and changing fragments
            if (trackFragment == 1) {
                if (txtFirstName.text.toString().isEmpty() || txtLastName.text.toString().isEmpty() || txtUsername.text.toString().isEmpty() || txtEmailAddressSignUp.text.toString().isEmpty() || txtPasswordSignUp.text.toString().isEmpty()) {
                    Toast.makeText(this, getString(R.string.signUpDisplayError), Toast.LENGTH_SHORT).show()
                } else {
                    val transactionForFragment_SignUpScreen_Two = manager.beginTransaction()
                    val fragmentForSignUpScreen_Two =
                        Fragment_SignUpScreen_Two()
                    transactionForFragment_SignUpScreen_Two.replace(
                        R.id.fragment_holder,
                        fragmentForSignUpScreen_Two
                    )
                    transactionForFragment_SignUpScreen_Two.addToBackStack("fragmentForSignUpScreen_Two")
                    transactionForFragment_SignUpScreen_Two.commit()
                    txtfragmentTrack.text = "Step ${trackFragment} of 3"
                    signUpDetails.add(txtFirstName.text.toString())
                    signUpDetails.add(txtLastName.text.toString())
                    signUpDetails.add(txtUsername.text.toString())
                    signUpDetails.add(txtEmailAddressSignUp.text.toString())
                    headerDetails.add(txtUsername.text.toString())
                }
            } else if (trackFragment == 2) {
                if (txtFamilyCountValue.text.toString()== "0" || gender == "Empty" || txtBirthDate.text.isEmpty() || imgProfile_Picture_SignUpScreen.drawable == null ) {
                    Toast.makeText(this, getString(R.string.signUpDisplayError), Toast.LENGTH_SHORT).show()
                } else {
                    signUpDetails.add(txtFamilyCountValue.text.toString())
                    signUpDetails.add(gender)
                    signUpDetails.add(txtBirthDate.text.toString())
//                    headerDetails.add(Fragment_SignUpScreen_Two.imgPath!!)
                    val transactionForFragment_SignUpScreenThree = manager.beginTransaction()
                    val fragmentForSignUpScreen_Three =
                        Fragment_SignUpScreenThree()
                    btnNextSignUpFragment.text = "Submit"
                    transactionForFragment_SignUpScreenThree.replace(
                        R.id.fragment_holder,
                        fragmentForSignUpScreen_Three
                    )
                    transactionForFragment_SignUpScreenThree.addToBackStack("fragmentForSignUpScreen_Three")
                    transactionForFragment_SignUpScreenThree.commit()
                    txtfragmentTrack.text = "Step ${trackFragment} of 3"
                }
            } else if (trackFragment == 3) {
                if (txtAboutYourself.text.toString().isEmpty() || txtSelfKnowledgePoint.text.toString().isEmpty() || t_c_checkbox.isChecked == false ) {
                    Toast.makeText(this, getString(R.string.signUpDisplayError), Toast.LENGTH_SHORT).show()
                } else {
                    signUpDetails.add(txtAboutYourself.text.toString())
                    signUpDetails.add(txtSelfKnowledgePoint.text.toString())
                    val intent = Intent(this, OTP_Screen::class.java)
                    startActivity(intent)
                }
            }
                btnNextSignUpFragment.text = "Next"
            }
        }
    }
