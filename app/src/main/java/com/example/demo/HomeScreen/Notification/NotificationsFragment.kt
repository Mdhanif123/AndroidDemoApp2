package com.example.demo.HomeScreen.Notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.HomeScreen.Notification.NotificationAdapterClass
import com.example.demo.HomeScreen.Notification.NotificationDataClass
import com.example.demo.R
import kotlinx.android.synthetic.main.fragment_notifications.*
import java.util.ArrayList

class NotificationsFragment : Fragment() {

    // declaring variables
    private var NotificationList = ArrayList<NotificationDataClass>()
    private lateinit var adapter: NotificationAdapterClass

    // On create view
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    // on view created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NotificationRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        adapter = NotificationAdapterClass(NotificationList)
        NotificationRecyclerView.adapter = adapter

        //passing data to recycler view
        dataNotifications()
    }

    fun dataNotifications() {
        var notification: NotificationDataClass
        for (id in 0..10) {
            notification =
                NotificationDataClass(
                    id,
                    "New Notification"
                )
            NotificationList.add(notification)
        }

    }
}
