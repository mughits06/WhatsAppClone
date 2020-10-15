package com.mughitszufar.whatsappclown.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mughitszufar.whatsappclown.activity.MainActivity
import com.mughitszufar.whatsappclown.fragment.ChatsFragment
import com.mughitszufar.whatsappclown.fragment.StatusListFragment
import com.mughitszufar.whatsappclown.fragment.StatusUpdateFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val chatsFragment = ChatsFragment()
    private val statusUpdateFragment = StatusUpdateFragment()
    private val statusListFragment = StatusListFragment()

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> statusUpdateFragment
            1 -> chatsFragment
            2 -> statusListFragment
            else -> chatsFragment

        }

    }
    override fun getCount(): Int {
        return 3
    }
}