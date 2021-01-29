package com.alfa_sc1.mastur

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.alfa_sc1.mastur.adapter.LoginActivityViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class LoginActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mViewPager = findViewById<ViewPager>(R.id.viewPager)
        val mTabLayout = findViewById<TabLayout>(R.id.tabLayout)

        mViewPager.adapter = LoginActivityViewPagerAdapter(supportFragmentManager)
        mTabLayout.setupWithViewPager(mViewPager)
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }
    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(this, "You are offline",
                Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You are online",
                Toast.LENGTH_LONG).show()
        }
    }
}