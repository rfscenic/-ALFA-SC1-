package com.alfa_sc1.mastur.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alfa_sc1.mastur.DaftarFragment
import com.alfa_sc1.mastur.LoginFragment

class LoginActivityViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    private val pages = listOf<Fragment>(
        DaftarFragment(),
        LoginFragment()
    )

    override fun getCount() = pages.size

    override fun getItem(position: Int) = pages[position]

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Daftar"
            else -> "Login"
        }
    }

}