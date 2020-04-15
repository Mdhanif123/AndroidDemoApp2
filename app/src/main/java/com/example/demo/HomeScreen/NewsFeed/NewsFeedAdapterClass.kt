package com.example.demo.HomeScreen.NewsFeed

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.TintableCompoundDrawablesView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.demo.R
import com.example.demo.R.color.Teal
import com.example.demo.R.color.btnColor
import java.util.ArrayList

class NewsFeedAdapterClass(newsFeedArrayList: ArrayList<NewsFeedDataClass>): Adapter<NewsFeedAdapterClass.NewsFeedViewHolder>() {

    //Declaring variables

    lateinit var context:Context
    internal var newsFeedArrayList = ArrayList<NewsFeedDataClass>()
    var totalLike = 0
    var totalComment = 0
    var clicked = false
    init {
        this.newsFeedArrayList = newsFeedArrayList
    }

    //On Create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_feed_recycler_view_list,parent,false)
        context = parent.context
        return NewsFeedViewHolder(v)
    }

    //Get Item Count
    override fun getItemCount(): Int {
        return newsFeedArrayList.size
    }

    //OnBind View Holder
    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {

        val newsFeedDataClass = newsFeedArrayList[position]

        //Setting Data
        holder.txtUserName.text = newsFeedDataClass.postUserName
        holder.txtTotalLikes.text = newsFeedDataClass.totalLikes.toString() + " Likes"
        holder.txtTotalComments.text = newsFeedDataClass.totalComments.toString() + " Comments"
        holder.txtPostStatus.text = newsFeedDataClass.postStatus
        holder.imgprofilePicture.setImageResource(newsFeedDataClass.profilePicture)

            // If there's no image in status hiding image view
        if (newsFeedDataClass.statusPicture == 0) {
            holder.postPicture.visibility = View.GONE
        } else {
            holder.postPicture.setImageResource(newsFeedDataClass.statusPicture)
            holder.postPicture.visibility = View.VISIBLE
        }
        //On Click Like Button setting total likes label
        holder.btnLike.setOnClickListener {
            clicked = !clicked
            if (clicked) {
                totalLike++
                holder.txtTotalLikes.text = "${totalLike} Likes"
                tintDrawableOnChecked(holder.btnLike.compoundDrawables[0])
            } else {
                tintDrawableOnUnChecked(holder.btnLike.compoundDrawables[0])
                totalLike--
                holder.txtTotalLikes.text = "0 Likes"
            }
        }

    }

    // function for Setting Like Button Drawable color
    fun tintDrawableOnChecked(drawable: Drawable) {
        drawable.setTint(context.getColor(btnColor))
    }
    // func for setting Like Button drawable color on uncheck
    fun tintDrawableOnUnChecked(drawable: Drawable) {
        drawable.setTint(context.getColor(Teal))
    }

    //NewsFeed Holder Class
    class NewsFeedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        internal var txtUserName: TextView
        internal var txtTotalLikes: TextView
        internal var txtTotalComments: TextView
        internal var txtPostStatus: TextView
        internal var imgprofilePicture: ImageView
        internal var postPicture: ImageView
        internal var btnLike: Button

        init {

            imgprofilePicture = itemView.findViewById<View>(R.id.postUserProfilePicture) as ImageView
            postPicture = itemView.findViewById<View>(R.id.postImage) as ImageView
            txtUserName = itemView.findViewById<View>(R.id.postUserName) as TextView
            txtTotalLikes = itemView.findViewById<View>(R.id.txtTotalLikes) as TextView
            txtTotalComments = itemView.findViewById<View>(R.id.txtTotalComments) as TextView
            txtPostStatus = itemView.findViewById<View>(R.id.postStatus) as TextView
            btnLike = itemView.findViewById(R.id.btnLike) as Button
        }

    }

}