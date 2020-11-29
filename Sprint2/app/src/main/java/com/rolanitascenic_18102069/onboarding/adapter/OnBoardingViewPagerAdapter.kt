package com.rolanitascenic_18102069.onboarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.rolanitascenic_18102069.onboarding.R
import com.rolanitascenic_18102069.onboarding.model.OnBoardingData

class OnBoardingViewPagerAdapter(private var context: Context, private var onBoardingDataList: List<OnBoardingData>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, null);
        val imageView: ImageView
        val title: TextView
        val description: TextView

        // Untuk mencari onboarding_Screen_layout dengan id

        imageView = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.titleOnBoarding);
        description = view.findViewById(R.id.descriptionOnBoarding);

        imageView.setImageResource(onBoardingDataList[position].ImageUrl)
        title.text = onBoardingDataList[position].title
        description.text = onBoardingDataList[position].description

        container.addView(view)

        return view
    }
}