package com.alfa_sc1.mastur.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfa_sc1.mastur.DetailPenginapanActivity
import com.alfa_sc1.mastur.MyDataPenginapan
import com.alfa_sc1.mastur.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_penginapan.view.*

class ListPenginapanMyDataAdapter (private val listMyDataPenginapan: ArrayList<MyDataPenginapan>, val context: Context) :
    RecyclerView.Adapter<ListPenginapanMyDataAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_penginapan, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val myData = listMyDataPenginapan[position]
        holder.bind(listMyDataPenginapan[position])
        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailPenginapanActivity::class.java)
            moveWithObjectIntent.putExtra(DetailPenginapanActivity.EXTRA_MYDATA, myData)
            context.startActivity(moveWithObjectIntent)
        }
    }
    override fun getItemCount(): Int = listMyDataPenginapan.size
    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(myData: MyDataPenginapan){
            with(itemView){
                Glide.with(itemView.context)
                    .load(myData.photo_penginapan)
                    .apply(RequestOptions().override(55, 55))
                    .into(img_item_photo_penginapan)
                tv_item_name_penginapan.text = myData.name_penginapan
                tv_item_location_penginapan.text = myData.location_penginapan
                tv_item_harga_penginapan.text = myData.harga_penginapan
            }
        }
    }


}