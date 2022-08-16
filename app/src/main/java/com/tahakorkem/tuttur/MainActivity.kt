package com.tahakorkem.tuttur

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.tahakorkem.tuttur.activity.BaseActivity
import com.tahakorkem.tuttur.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNavigation.setOnItemSelectedListener {
            if (binding.bottomNavigation.selectedItemId == it.itemId) {
                return@setOnItemSelectedListener false
            }
            when(it.itemId) {
                R.id.item_movies -> {
                    replaceFragment<MovieListFragment>()
                    true
                }
                R.id.item_posts -> {
                    replaceFragment<PostListFragment>()
                    true
                }
                R.id.item_countries -> {
                    replaceFragment<CountryListFragment>()
                    true
                }
                else -> false
            }
        }

        replaceFragment<MovieListFragment>()
    }

    private inline fun <reified T : Fragment> replaceFragment() {
        supportFragmentManager.commit {
            replace<T>(R.id.fragment_container)
        }
    }

}