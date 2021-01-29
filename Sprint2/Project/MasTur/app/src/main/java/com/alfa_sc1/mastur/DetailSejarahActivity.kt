package com.alfa_sc1.mastur

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_sejarah.*
import kotlinx.android.synthetic.main.content_scrolling_sejarah.*

class DetailSejarahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_sejarah)
        setSupportActionBar(findViewById(R.id.toolbar))

        val myData by getParcelableExtra<MyDataSejarah>(DetailSejarahActivity.EXTRA_MYDATA)
        supportActionBar?.title = myData?.nama.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_detail_description_sejarah.text = myData?.description.toString()
        Glide.with(this)
            .load(myData?.url.toString())
            .apply(RequestOptions().override(700, 700))
            .into(iv_detail_photo)
        setSupportActionBar(findViewById(R.id.toolbar))
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