package com.alfa_sc1.mastur

import ProfilMyDataAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfa_sc1.mastur.adapter.HomeMyDataAdapter
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {
    private val list = ArrayList<MyData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        rv_mydata.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerList()
        navigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.action_list -> {
                    showRecyclerList()
                }
                R.id.action_grid -> {
//                    showRecyclerGrid()
                }
                R.id.action_cardview -> {
                    showRecyclerCardView()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    fun getListMyDatas(): ArrayList<MyData> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataImage = resources.getStringArray(R.array.data_image)
        val dataTextSejarah = resources.getStringArray(R.array.text_sejarah)
        val listMyData = ArrayList<MyData>()
        for (position in dataTextSejarah.indices) {
            val myData = MyData(
                dataName[position],
                dataDescription[position],
                dataImage[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }

    private fun showRecyclerList() {
        rv_mydata.layoutManager = LinearLayoutManager(this)
        val listMyDataAdapter = HomeMyDataAdapter(list)
        rv_mydata.adapter = listMyDataAdapter
    }

//    private fun showRecyclerGrid() {
//        rv_mydata.layoutManager = GridLayoutManager(this, 2)
//        val gridMyDataAdapter = NearbyMyDataAdapter(list)
//        rv_mydata.adapter = gridMyDataAdapter
//    }

    private fun showRecyclerCardView() {
        rv_mydata.layoutManager = LinearLayoutManager(this)
        val cardViewMyDataAdapter = ProfilMyDataAdapter (list)
        rv_mydata.adapter = cardViewMyDataAdapter
    }
}