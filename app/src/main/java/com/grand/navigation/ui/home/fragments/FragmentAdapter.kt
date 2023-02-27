package com.brokerageInsurance.admincrm.ui.moreDetails.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grand.navigation.ui.home.fragments.food.FoodFragment
import com.grand.navigation.ui.home.fragments.restaurant.ResturentFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> ResturentFragment()
            else -> FoodFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}