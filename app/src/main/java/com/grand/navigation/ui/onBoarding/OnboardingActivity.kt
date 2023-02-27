package com.grand.navigation.ui.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grand.navigation.MainActivity
import com.grand.navigation.databinding.ActivityOnboardingBinding
import org.koin.android.ext.android.inject

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val firstUsing: FirstUsing by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.continueBtn.setOnClickListener{
            firstUsing.setAppUsed(false)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}