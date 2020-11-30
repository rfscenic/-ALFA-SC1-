package com.alfa_sc1.mastur.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfa_sc1.mastur.DetailSejarahActivity
import com.alfa_sc1.mastur.MyDataSejarah
import com.alfa_sc1.mastur.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_sejarah.view.*

class ListSejarahMyDataAdapter (private val listMyDataSejarah: ArrayList<MyDataSejarah>, val context: Context) :
RecyclerView.Adapter<ListSejarahMyDataAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_sejarah, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val myData = listMyDataSejarah[position]
        holder.bind(listMyDataSejarah[position])
        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailSejarahActivity::class.java)
            moveWithObjectIntent.putExtra(DetailSejarahActivity.EXTRA_MYDATA, myData)
            context.startActivity(moveWithObjectIntent)
        }
    }
    override fun getItemCount(): Int = listMyDataSejarah.size
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myData: MyDataSejarah) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(myData.photo)
                    .apply(RequestOptions().override(55, 55))
                    .into(img_item_photo_sejarah)
                tv_item_name_sejarah.text = myData.name
                tv_item_description_sejarah.text = myData.description
            }
        }
    }

}