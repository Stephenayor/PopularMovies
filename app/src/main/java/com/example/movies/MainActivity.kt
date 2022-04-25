package com.example.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.view.PagerAdapter
import com.example.movies.view.PopularMoviesFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


//        binding.pager.adapter = PagerAdapter(supportFragmentManager)
//        binding.tabLayout.setupWithViewPager(binding.pager)

        val navController = findNavController(R.id.nav_host_fragment)
    }

}