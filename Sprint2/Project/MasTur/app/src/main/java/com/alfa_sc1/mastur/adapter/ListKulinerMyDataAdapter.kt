package com.alfa_sc1.mastur.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfa_sc1.mastur.DetailKulinerActivity
import com.alfa_sc1.mastur.DetailSejarahActivity
import com.alfa_sc1.mastur.MyDataKuliner
import com.alfa_sc1.mastur.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_kuliner.view.*

class ListKulinerMyDataAdapter (private val listMyDataKuliner: ArrayList<MyDataKuliner>, val context: Context) :
RecyclerView.Adapter<ListKulinerMyDataAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_kuliner, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val myData = listMyDataKuliner[position]
        holder.bind(listMyDataKuliner[position])
        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailKulinerActivity::class.java)
            moveWithObjectIntent.putExtra(DetailKulinerActivity.EXTRA_MYDATA, myData)
            context.startActivity(moveWithObjectIntent)
        }
    }
    override fun getItemCount(): Int = listMyDataKuliner.size
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myData: MyDataKuliner) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(myData.photo_kuliner)
                    .apply(RequestOptions().override(55, 55))
                    .into(img_item_photo_kuliner)
                tv_item_name_kuliner.text = myData.name_kuliner
                tv_item_description_kuliner.text = myData.description_kuliner
            }
        }
    }

}