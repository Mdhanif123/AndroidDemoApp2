package com.example.demo.HomeScreen.Notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R

class NotificationAdapterClass (val NotificationList: ArrayList<NotificationDataClass>): RecyclerView.Adapter<NotificationAdapterClass.ViewHolder>(){

    // On create view Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_feed_list, parent, false)
        return ViewHolder(view)
    }

    //get item count
    override fun getItemCount(): Int {
        return NotificationList.size
    }

    // on bind view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notificationList = NotificationList[position]
        holder.txtNotificationMessage.text=notificationList.title
    }

    // ViewHolder Class
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var txtNotificationMessage: TextView
        init {
            txtNotificationMessage = itemView.findViewById<View>(R.id.notificationMessage) as TextView
        }
    }

}