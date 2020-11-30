package com.alfa_sc1.mastur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfa_sc1.mastur.adapter.ListSejarahMyDataAdapter
import kotlinx.android.synthetic.main.activity_sejarah.*

class SejarahActivity : AppCompatActivity() {
    private val list = ArrayList<MyDataSejarah>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sejarah)
        rv_mydata_sejarah.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerList()
    }
    fun getListMyDatas(): ArrayList<MyDataSejarah> {
        val dataName = resources.getStringArray(R.array.data_name_sejarah)
        val dataDescription = resources.getStringArray(R.array.data_description_sejarah)
        val dataPhoto = resources.getStringArray(R.array.data_photo_sejarah)
        val listMyData = ArrayList<MyDataSejarah>()
        for (position in dataName.indices) {
            val myData = MyDataSejarah(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }
    private fun showRecyclerList() {
        rv_mydata_sejarah.layoutManager = LinearLayoutManager(this)
        val listMyDataAdapter = ListSejarahMyDataAdapter(list, this@SejarahActivity)
        rv_mydata_sejarah.adapter = listMyDataAdapter
    }
}