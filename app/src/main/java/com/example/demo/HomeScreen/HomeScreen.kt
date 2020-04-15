package com.example.demo.HomeScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.demo.HomeScreen.NewsFeed.NewsFeedFragment
import com.example.demo.HomeScreen.Notification.NotificationsFragment
import com.example.demo.HomeScreen.Profile.ProfileScreen
import com.example.demo.R
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // display First Fragment
        val newsFeedFragment = NewsFeedFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder_for_homeScreen,newsFeedFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()

        // changing fragment according to bottom navigation bar item
        bottom_NavigationBar.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.newsFeed -> {
                    HomeScreenHeaderText.text = "Simform"
                    var newsFeedFragment =
                        NewsFeedFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_holder_for_homeScreen,newsFeedFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
                R.id.notifications -> {
                    HomeScreenHeaderText.text = "Notifications"
                    var notificationsFragment =
                        NotificationsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_holder_for_homeScreen,notificationsFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
                R.id.profile -> {
                    HomeScreenHeaderText.text = "Profile"
                    var profileFragment =
                        ProfileScreen()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_holder_for_homeScreen,profileFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
            }
            true
        }
    }

    //Exit app on back pressed
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


}
