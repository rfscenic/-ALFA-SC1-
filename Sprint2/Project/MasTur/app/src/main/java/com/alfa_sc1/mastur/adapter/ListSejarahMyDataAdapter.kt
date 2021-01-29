package com.alfa_sc1.mastur.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfa_sc1.mastur.MyDataSejarah
import com.alfa_sc1.mastur.R
import com.alfa_sc1.mastur.SejarahAddUpdateActivity
import com.alfa_sc1.mastur.databinding.ItemListSejarahBinding
import com.alfa_sc1.mastur.helper.EXTRA_POSITION
import com.alfa_sc1.mastur.helper.EXTRA_QUOTE
import com.alfa_sc1.mastur.helper.REQUEST_UPDATE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_sejarah.view.*

class ListSejarahMyDataAdapter (private val activity: Activity):
RecyclerView.Adapter<ListSejarahMyDataAdapter.ListViewHolder>() {
    var listSejarah = ArrayList<MyDataSejarah>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_sejarah, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listSejarah.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listSejarah[position], position)
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSejarahBinding.bind(itemView)
        fun bind(myData: MyDataSejarah, position: Int) {
            binding.tvItemNameSejarah.text = myData.nama
            binding.tvItemLocationSejarah.text = myData.location
            Glide.with(itemView.context)
                .load(myData.url)
                .apply(RequestOptions().override(55, 55))
                .into(binding.imgItemPhotoSejarah)
            binding.itemSejarah.setOnClickListener{
                val intent = Intent(activity, SejarahAddUpdateActivity::class.java)
                intent.putExtra(EXTRA_POSITION, position)
                intent.putExtra(EXTRA_QUOTE, myData)
                activity.startActivityForResult(intent, REQUEST_UPDATE)
            }
        }
    }

}