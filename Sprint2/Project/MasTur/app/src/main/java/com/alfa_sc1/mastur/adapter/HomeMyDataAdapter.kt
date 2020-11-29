package com.alfa_sc1.mastur.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.alfa_sc1.mastur.MyData
import com.alfa_sc1.mastur.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.nav_home.*
import kotlinx.android.synthetic.main.nav_home.view.*

class HomeMyDataAdapter (private val listMyData: ArrayList<MyData>) :
    RecyclerView.Adapter<HomeMyDataAdapter.ListViewHolder>(){


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myData: MyData) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(myData.photo)
                    .apply(RequestOptions().override(200, 200))
                    .into(img_item_photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nav_home, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMyData.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMyData[position])
    }
}