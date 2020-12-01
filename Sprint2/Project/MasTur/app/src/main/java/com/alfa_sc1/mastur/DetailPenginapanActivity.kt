package com.alfa_sc1.mastur

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_penginapan.*
import kotlinx.android.synthetic.main.content_scrolling_penginapan.*

class DetailPenginapanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_penginapan)
        setSupportActionBar(findViewById(R.id.toolbar))

        val myData by getParcelableExtra<MyDataPenginapan>(DetailPenginapanActivity.EXTRA_MYDATA)
        supportActionBar?.title = myData?.name_penginapan.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_detail_description_penginapan.text = myData?.description_penginapan.toString()
        Glide.with(this)
            .load(myData?.photo_penginapan.toString())
            .apply(RequestOptions().override(700, 700))
            .into(iv_detail_photo_penginapan)
    }
    companion object {
        const val EXTRA_MYDATA = "extra_mydata"
    }
    private inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = lazy {
        intent.getParcelableExtra<T>(key)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}