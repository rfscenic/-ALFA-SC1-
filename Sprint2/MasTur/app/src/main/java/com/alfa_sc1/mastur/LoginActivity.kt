package com.alfa_sc1.mastur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.alfa_sc1.mastur.adapter.LoginActivityViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mViewPager = findViewById<ViewPager>(R.id.viewPager)
        val mTabLayout = findViewById<TabLayout>(R.id.tabLayout)

        mViewPager.adapter = LoginActivityViewPagerAdapter(supportFragmentManager)
        mTabLayout.setupWithViewPager(mViewPager)
    }
}