package com.alfa_sc1.mastur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfa_sc1.mastur.adapter.ListKulinerMyDataAdapter
import kotlinx.android.synthetic.main.activity_kuliner.*

class KulinerActivity : AppCompatActivity() {
    private val list = ArrayList<MyDataKuliner>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kuliner)
        rv_mydata_kuliner.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerList()
    }
    fun getListMyDatas(): ArrayList<MyDataKuliner> {
        val dataName = resources.getStringArray(R.array.data_name_kuliner)
        val dataDescription = resources.getStringArray(R.array.data_description_kuliner)
        val dataPhoto = resources.getStringArray(R.array.data_photo_kuliner)
        val dataHarga = resources.getStringArray(R.array.data_harga_kuliner)
        val dataLocation = resources.getStringArray(R.array.data_location_kuliner)
        val listMyData = ArrayList<MyDataKuliner>()
        for (position in dataName.indices) {
            val myData = MyDataKuliner(
                dataName[position],
                dataDescription[position],
                dataPhoto[position],
                dataHarga[position],
                dataLocation[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }
    private fun showRecyclerList() {
        rv_mydata_kuliner.layoutManager = LinearLayoutManager(this)
        val listMyDataAdapter = ListKulinerMyDataAdapter(list, this@KulinerActivity)
        rv_mydata_kuliner.adapter = listMyDataAdapter
    }
}