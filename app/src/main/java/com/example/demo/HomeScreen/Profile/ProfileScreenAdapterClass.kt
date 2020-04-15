package com.example.demo.HomeScreen.Profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R

interface AdapterOnCLick {
    fun onClick()
}

class ProfileScreenAdapterClass (val adapterOnCLick: AdapterOnCLick,val ProfileDataList: ArrayList<ProfileScreenDataClass>,val profileHeaderList: ArrayList<profileScreenHeaderClass>): RecyclerView.Adapter<ProfileScreenAdapterClass.Companion.ProfileScreenViewHolder>() {

    // On create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileScreenViewHolder {
        val rv: View
        val holder: ProfileScreenViewHolder

        if (viewType == type_head)
        {
            rv =LayoutInflater.from(parent!!.context).inflate(R.layout.profile_header_list_,parent,false)
            holder =
                ProfileScreenViewHolder(
                    rv,
                    viewType
                )
            return holder
        } else {
            rv =LayoutInflater.from(parent!!.context).inflate(R.layout.profile_list,parent,false)
            holder =
                ProfileScreenViewHolder(
                    rv,
                    viewType
                )
            return holder
        }
    }

    // on bind view holder
    override fun onBindViewHolder(holder: ProfileScreenViewHolder, position: Int) {

        // if row passing row data
        if (holder.view_Type == type_list) {
            val profileList = ProfileDataList[position - 1]
            holder.userData.text = profileList.data
        }
        //if header passing header data
        else {
            val pfp = ProfileScreen()
            val headerData = profileHeaderList[position]
            holder.headerUserProfilePic.setImageURI(headerData.img)
            holder.headerUserName.text = headerData.userName
            holder.btnEditProfilePicture.setOnClickListener {
                adapterOnCLick.onClick()
            }

        }
    }

    // get item count
    override fun getItemCount(): Int {
     return ProfileDataList.size + 1
    }




    // Class Profilescreen view holder
    companion object {

        val type_head = 0
        val type_list = 1

        class ProfileScreenViewHolder(rv: View, viewType: Int) : RecyclerView.ViewHolder(rv) {

            var view_Type:Int = 0
            lateinit var userData: TextView
            lateinit var headerUserProfilePic:ImageView
            lateinit var headerUserName:TextView
            lateinit var btnEditProfilePicture: ImageButton
            init {
                //if row
                if (viewType == type_list) {
                    userData = rv.findViewById(R.id.dataOfUser) as TextView
                    view_Type = 1
                }
                // if header
                else if (viewType == type_head) {
                    headerUserProfilePic = rv.findViewById(R.id.imgUserProfilePicture) as ImageView
                    headerUserName = rv.findViewById(R.id.headerUserName)
                    btnEditProfilePicture = rv.findViewById(R.id.editProfilePicture) as ImageButton
                    view_Type = 0
                }
            }
        }
    }

    //Get item view type row or header
    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return type_head
        return type_list
    }
}