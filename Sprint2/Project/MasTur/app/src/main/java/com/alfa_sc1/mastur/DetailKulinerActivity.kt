package com.alfa_sc1.mastur

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_kuliner.*
import kotlinx.android.synthetic.main.content_scrolling_kuliner.*

class DetailKulinerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kuliner)
        setSupportActionBar(findViewById(R.id.toolbar_kuliner))

        val myData by getParcelableExtra<MyDataKuliner>(DetailKulinerActivity.EXTRA_MYDATA)
        supportActionBar?.title = myData?.name_kuliner.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_detail_description_kuliner.text = myData?.description_kuliner.toString()
        Glide.with(this)
            .load(myData?.photo_kuliner.toString())
            .apply(RequestOptions().override(700,700))
            .into(iv_detail_photo_kuliner)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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