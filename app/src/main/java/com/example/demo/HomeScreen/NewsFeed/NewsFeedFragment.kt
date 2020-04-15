package com.example.demo.HomeScreen.NewsFeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.HomeScreen.NewsFeed.NewsFeedAdapterClass
import com.example.demo.HomeScreen.NewsFeed.NewsFeedDataClass
import com.example.demo.R
import kotlinx.android.synthetic.main.fragment_news_feed.*
import java.util.ArrayList

class NewsFeedFragment : Fragment() {

    private var newsFeedArrayList = ArrayList<NewsFeedDataClass>()
    private lateinit var newsFeedAdapter: NewsFeedAdapterClass

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsFeedRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        newsFeedAdapter = NewsFeedAdapterClass(newsFeedArrayList )
        newsFeedRecyclerView.adapter = newsFeedAdapter

        dataForNewsFeed()

    }

    //passing data to recyclerview
    fun dataForNewsFeed() {
        var newsFeedDataClass =
            NewsFeedDataClass(
                1,
                0,
                0,
                R.drawable.billgates,
                R.drawable.billgates,
                " Simform ",
                "Simform"
            )
        newsFeedArrayList.add(newsFeedDataClass)

        newsFeedDataClass =
            NewsFeedDataClass(
                2,
                0,
                0,
                R.drawable.simformlogo,
                0,
                "Simform",
                "Simform"
            )
        newsFeedArrayList.add(newsFeedDataClass)
        newsFeedDataClass =
            NewsFeedDataClass(
                3,
                3,
                3,
                R.drawable.simformlogo,
                R.drawable.simformlogo,
                "Simform",
                "Simform"
            )
        newsFeedArrayList.add(newsFeedDataClass)



    }
}
