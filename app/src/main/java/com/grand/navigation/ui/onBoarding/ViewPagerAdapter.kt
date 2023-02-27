package com.grand.navigation.ui.onBoarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.grand.navigation.R


class ViewPagerAdapter(private val context: Context) : PagerAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private val imgArray = arrayOf(
        R.drawable.onboarding_one,
        R.drawable.onboarding_two,
        R.drawable.onboarding_three
    )

    private val headArray = arrayOf(
        context.getString(R.string.onboard_title_one),
        context.getString(R.string.even_to_spa),
        context.getString(R.string.pickup_deli)


    )
    private val arrayDescription = arrayOf(
        context.getString(R.string.onboard_description_one),
        context.getString(R.string.onboard_description_one),
        context.getString(R.string.onboard_description_one)
    )

    override fun getCount(): Int {
        return headArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.slider, container, false)

        val img = view.findViewById<ImageView>(R.id.image)
        val head = view.findViewById<TextView>(R.id.txt_head)
        val description = view.findViewById<TextView>(R.id.txt_description)

        img.setImageResource(imgArray[position])
        head.text = headArray[position].toString()
        description.text = arrayDescription[position].toString()

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}