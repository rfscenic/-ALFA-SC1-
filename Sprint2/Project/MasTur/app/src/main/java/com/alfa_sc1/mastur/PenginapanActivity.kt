package com.alfa_sc1.mastur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfa_sc1.mastur.adapter.ListPenginapanMyDataAdapter
import kotlinx.android.synthetic.main.activity_penginapan.*

class PenginapanActivity : AppCompatActivity() {
    private val list = ArrayList<MyDataPenginapan>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penginapan)
        rv_mydata_penginapan.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerList()
    }
    fun getListMyDatas(): ArrayList<MyDataPenginapan> {
        val dataName = resources.getStringArray(R.array.data_name_penginapan)
        val dataDescription = resources.getStringArray(R.array.data_description_penginapan)
        val dataPhoto = resources.getStringArray(R.array.data_photo_penginapan)
        val dataLocation = resources.getStringArray(R.array.data_location_penginapan)
        val listMyData = ArrayList<MyDataPenginapan>()
        for (position in dataName.indices) {
            val myData = MyDataPenginapan(
                dataName[position],
                dataDescription[position],
                dataPhoto[position],
                dataLocation[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }
    private fun showRecyclerList() {
        rv_mydata_penginapan.layoutManager = LinearLayoutManager(this)
        val listMyDataAdapter = ListPenginapanMyDataAdapter(list, this@PenginapanActivity)
        rv_mydata_penginapan.adapter = listMyDataAdapter
    }
}