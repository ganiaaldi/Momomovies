package com.gadidev.momomovies

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gadidev.momomovies.databinding.ActivityMainBinding
import com.gadidev.momomovies.utils.ChangeToolbarTitle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ChangeToolbarTitle {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainNavControl: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainBottomNav: BottomNavigationView
    private lateinit var host: NavHostFragment
    private var mainToolbar: Toolbar? = null
    private var mainToolbarTitle: TextView? = null
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupDestination()
        setupNavController()
        setupActionBar(mainNavControl, appBarConfiguration)
        showBottomMenu(mainNavControl)
    }

    private fun setupDestination() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.favoriteFragment))
    }

    private operator fun String.invoke(function: () -> Unit): () -> Unit {
        return function
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.primary_navigation_fragment).navigateUp(appBarConfiguration)
    }

    override fun updateTitle(title: String) {
        mainToolbarTitle?.text = title
    }

    override fun toolbarAction(onClickListener: View.OnClickListener) {
        mainToolbar?.setOnClickListener(onClickListener)
    }


    override fun showToolbar(show: Boolean) {
        mainToolbar?.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onBackPressed() {
        findNavController(R.id.primary_navigation_fragment).navigateUp(appBarConfiguration)
        if (doubleBackToExitPressedOnce) {
            finish()
        }
        this.doubleBackToExitPressedOnce = true
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


    private fun setupNavController() {
        host = supportFragmentManager
            .findFragmentById(R.id.primary_navigation_fragment) as NavHostFragment? ?: return
        mainNavControl = host.navController
    }

    private fun showBottomMenu(navController: NavController) {
        mainBottomNav = findViewById(R.id.menu_bottom)
        mainBottomNav.setupWithNavController(navController)

        mainNavControl.addOnDestinationChangedListener { _, destination, _ ->
            if (
                destination.id == R.id.favoriteFragment ||
                destination.id == R.id.homeFragment
            ) {
//                mainToolbarTitle?.text = destination.label
                showBottomMenu()
            } else {
                hideBottomMenu()
            }
        }
    }


    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        mainToolbar = binding.toolbar
        mainToolbarTitle = binding.toolbarTitle
        setSupportActionBar(mainToolbar)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun hideBottomMenu() {
        // bottom_navigation is BottomNavigationView
        with(mainBottomNav) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .alpha(0f)
                    .withEndAction { visibility = View.GONE }
                    .duration = 500
            }
        }
    }

    private fun showBottomMenu() {
        // bottom_navigation is BottomNavigationView
        with(mainBottomNav) {
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .duration = 500
        }
    }
}