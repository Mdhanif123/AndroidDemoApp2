package com.example.demo.SignUpScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class Fragment_SignUpScreenThree : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__sign_up_screen_three, container, false)
    }

    override fun onResume() {
        super.onResume()
        val parentActivity = activity as SignUp
        parentActivity.trackFragment = 3
        parentActivity.txtfragmentTrack.text = "Step 3 of 3"
    }

}
