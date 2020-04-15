package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_terms_and_condtions.*
import kotlinx.android.synthetic.main.fragment__sign_up_screen_three.*

class TermsAndCondtionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_condtions)

        webViewForTandC.loadUrl(getString(R.string.TandCFileLocation))
    }
}
