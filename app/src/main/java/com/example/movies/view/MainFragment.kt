package com.example.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.movies.R
import com.example.movies.databinding.FragmentMainBinding
import com.example.movies.databinding.FragmentPopularMoviesBinding


open class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater, R.layout.fragment_main, container,
            false
        )

        binding.pager.adapter = fragmentManager?.let { PagerAdapter(it) }
        binding.tabLayout.setupWithViewPager(binding.pager)
        return binding.root
    }
}