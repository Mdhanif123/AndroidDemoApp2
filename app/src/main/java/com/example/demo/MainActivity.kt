package com.example.demo

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.example.demo.SignUpScreen.SignUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ///Making Header Logo Circular
        val image = BitmapFactory.decodeResource(resources,R.drawable.simformlogo)
        val round = RoundedBitmapDrawableFactory.create(resources,image)
        round.isCircular = true
        imgSimformLogo.setImageDrawable(round)


        fun emailValidation(): Boolean {

            val emailInput = txtEmailAddressLogin.text.toString().trim()
            if (emailInput.isEmpty()) {
                txtEmailAddressLogin.error="Field can't be empty"
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
                txtEmailAddressLogin.error="Please enter a valid email address"
                return false
            } else{
                txtEmailAddressLogin.error=null
                return true
            }
        }

        fun passwordValidation(): Boolean {

            val passwordInput : String=txtPassword.text.toString().trim()
            if (passwordInput.isEmpty()) {
                txtPassword.error="Field can't be empty"
                return false
            } else if (passwordInput.length < 8) {
                txtPassword.error="Please enter valid password"
                return false
            }else {
                txtPassword.error=null
                return true
            }
        }

        ///Main Login Button OnClick Listener
        btnLogin.setOnClickListener {
            if (emailValidation() && passwordValidation()){
                val intent = Intent(this, OTP_Screen::class.java)
                startActivity(intent)}
        }

        lateinit var constraintSet: ConstraintSet

        ///Footer Login Button OnClick Listener
        btnLoginFooter.setOnClickListener {
            Login_Layout.visibility = View.VISIBLE
            btnLoginFooter.visibility = View.GONE

//            constraintSet.clone(main_page_footer_layout)
//            constraintSet.connect(R.id.btnSignUp,ConstraintSet.START,R.id.main_page_footer_layout,ConstraintSet.RIGHT)
//            constraintSet.connect(R.id.btnSignUp,ConstraintSet.END,R.id.main_page_footer_layout,ConstraintSet.END)
//            constraintSet.applyTo(main_page_footer_layout)

        }

        ///Footer SignUp Button OnClick Listener
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
}
