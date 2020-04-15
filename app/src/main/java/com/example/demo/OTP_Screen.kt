package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demo.HomeScreen.HomeScreen
import kotlinx.android.synthetic.main.activity_o_t_p__screen.*

class OTP_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p__screen)

        // Submit otp validation and moving to home screen
        btnSubmitOTP.setOnClickListener {
            if (otpDigitOne.text.isEmpty() || otpDigitTwo.text.isEmpty() || otpDigitThree.text.isEmpty() || otpDigitFour.text.isEmpty()) {
                Toast.makeText(this,getString(R.string.otpError), Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            }
        }

    }

}
