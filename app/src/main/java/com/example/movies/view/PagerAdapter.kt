package com.example.movies.view

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movies.view.PopularMoviesFragment
import com.example.movies.view.TopRatedMoviesFragment

class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return PopularMoviesFragment()
            }
            1 -> {
                return TopRatedMoviesFragment()
            }

            else -> {
                return PopularMoviesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "POPULAR"
            }
            1 -> {
                return "TOP RATED"
            }
        }
        return super.getPageTitle(position)
    }


}