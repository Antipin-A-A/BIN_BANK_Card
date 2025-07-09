package com.example.test_bin_bank_card.ui.root

import androidx.appcompat.app.AppCompatActivity
import androidx.core.bundle.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.test_bin_bank_card.R
import com.example.test_bin_bank_card.databinding.ActivityRootBinding


class RootActivity : AppCompatActivity() {

    private var binding: ActivityRootBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding?.bottomNavigation?.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.detailsFragment,
//                R.id.filterFragment,
//                R.id.industryFragment,
//                R.id.areasFilterFragment,
//                R.id.countriesFragment,
//                R.id.regionsFragment -> {
//                    binding?.bottomNavigation?.isVisible = false
//                }
//
//                else -> {
//                    binding?.bottomNavigation?.isVisible = true
//                }
//            }
//        }
    }
}
