package com.grand.navigation.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.brokerageInsurance.admincrm.ui.moreDetails.fragments.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.grand.navigation.R
import com.grand.navigation.databinding.FragmentHomeBinding
import com.grand.navigation.ui.home.fragments.food.MyObseravble


class HomeFragment : Fragment(R.layout.fragment_home){
   // private val args: MoreDetailsFragmentArgs by navArgs()
   private lateinit var viewModel: MyObseravble
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val fm: FragmentManager = requireActivity().supportFragmentManager

        val adapter = FragmentAdapter(fm, lifecycle)
        binding.apply {
            viewPager2.adapter = adapter
            tabLayout.addTab(tabLayout.newTab().setText("Food Item"))
            tabLayout.addTab(binding.tabLayout.newTab().setText("Restaurant"))
            tabLayout.setBackgroundResource(R.drawable.tablayout_background)
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            buttonLogin.setOnClickListener{
                val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                findNavController().navigate(action)
            }

            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    binding.viewPager2.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

            binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                }
            })

            binding.searchBar.setOnEditorActionListener { _, actionId, _ ->
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val searchQuery: String = searchBar.text.toString()
                    if (searchQuery.isNotEmpty()){
                        Log.e("TAG", "onViewCreated: $searchQuery")
                        viewModel.data.value=searchQuery
                    }else{
                        Log.e("TAG", "onViewCreated: Empty")
                        binding.searchBar.error = "type any thing to search"
                        binding.searchBar.focusable = View.FOCUSABLE
                    }

                    handled = true
                }
                handled
            }
        }
        viewModel = activity?.run {
            ViewModelProvider(this).get(MyObseravble::class.java)
        } ?: throw Exception("Invalid Activity")
    }


}