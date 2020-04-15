package com.example.demo.SignUpScreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.demo.OTP_Screen
import com.example.demo.R
import com.example.demo.TermsAndCondtionsActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment__sign_up_screen_three.view.*

class Fragment_SignUpScreenThree : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment__sign_up_screen_three, container, false)

        view.termsAndConditions.setOnClickListener {
            Toast.makeText(view.context,"CheckBox",Toast.LENGTH_LONG).show()
            val intent = Intent(view.context, TermsAndCondtionsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        val parentActivity = activity as SignUp
        parentActivity.trackFragment = 3
        parentActivity.txtfragmentTrack.text = "Step 3 of 3"
    }

}
