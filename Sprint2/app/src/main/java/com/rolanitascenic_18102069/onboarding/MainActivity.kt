package com.rolanitascenic_18102069.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rolanitascenic_18102069.onboarding.adapter.OnBoardingViewPagerAdapter
import com.rolanitascenic_18102069.onboarding.model.OnBoardingData
import java.nio.file.Files.size

class MainActivity : AppCompatActivity() {

    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(restorePrefData()){
            val i = Intent(applicationContext, HomeActivity::class.java)
            startActivity(i)
            finish()
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        supportActionBar!!.hide()

        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.button_next)

        // Memasukkan data ke dalam model class

        val onBoardingData:MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Mas Tur"," ",R.drawable.logo_mastur))
        onBoardingData.add(OnBoardingData("Mas Tur","Memandu Anda \n Menjelajah dan Mengeksplore Banyumas", R.drawable.logo_tour))
        onBoardingData.add(OnBoardingData("Mas Tur","Mengenai Sejarah \n yang Ada di banyumas", R.drawable.logo_sejarah))
        onBoardingData.add(OnBoardingData("Mas Tur","Dilengkapi dengan E-ticket wisata dan \n informasi tempat penginapan dan kuliner terdekat", R.drawable.logo_penginapan_tiket))
        onBoardingData.add(OnBoardingData("Ayo Jelajahi Banyumas \n Sekarang!"," ", R.drawable.logo_mastur))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        next?.setOnClickListener{
            if(position < onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if(position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if(tab.position == onBoardingData.size - 1){
                    next!!.text = "Get Started"
                }else{
                    next!!.text = "Next"
                }
            }
        })
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){

        onBoardingViewPager = findViewById(R.id.screenPagerOnBoarding)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(context = this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)

    }

    private fun savePrefData(){

        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor? = sharedPreferences!!.edit()
        editor?.putBoolean("isFirstTimeRun", true)
        editor?.apply()

    }

    private fun restorePrefData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }

}