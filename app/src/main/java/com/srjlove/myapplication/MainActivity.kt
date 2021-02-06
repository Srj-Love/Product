package com.srjlove.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.srjlove.myapplication.adapter.ProdVpAdapter
import com.srjlove.myapplication.databinding.ActivityMainBinding
import com.srjlove.myapplication.view.DetailsFragment
import com.srjlove.myapplication.view.ProductFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mPagerAdapter: ProdVpAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        val fraList = arrayListOf(
            ProductFragment.newInstance(),
            DetailsFragment.newInstance()
        )
        mPagerAdapter = ProdVpAdapter(this, fraList)
        binding.vpProduct.adapter = mPagerAdapter
        TabLayoutMediator(binding.tlProducts, binding.vpProduct) { tab, position ->
            tab.text =
                when (position) {
                    0 -> getString(R.string.add_products)
                    1 -> getString(R.string.all_products)
                    else -> getString(R.string.add_products)
                }
            binding.vpProduct.setCurrentItem(tab.position, false)
        }.attach()

    }


}