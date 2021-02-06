package com.srjlove.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

class ProdVpAdapter(
    fragment: FragmentActivity,
    private val fraList: ArrayList<Fragment>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = fraList.size


    override fun createFragment(position: Int): Fragment {
        return fraList[position]
    }
}