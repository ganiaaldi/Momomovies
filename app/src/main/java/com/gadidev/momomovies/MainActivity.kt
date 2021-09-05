package com.gadidev.momomovies

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ChangeToolbarTitle {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainNavControl: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainBottomNav: BottomNavigationView
    private lateinit var host: NavHostFragment
    private var mainToolbar: Toolbar? = null
    private var mainToolbarTitle: TextView? = null
    private lateinit var drawerLayout: DrawerLayout
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupDestination()
        setupNavController()
        setupDrawer()
        setupActionBar(mainNavControl, appBarConfiguration)
        showNavigationMenu(mainNavControl)
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
        Toast.makeText(this, "Press again to exit apps.",Toast.LENGTH_SHORT).show()
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

    private fun setupDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,R.id.favoriteFragment),
            drawerLayout
        )

        drawerLayout!!.addDrawerListener(object: DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(newState: Int) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerClosed(drawerView: View) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerOpened(drawerView: View) {
                loadAvatar()
            }
        })
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
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                hideBottomMenu()
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
    }

    private fun showNavigationMenu(navController: NavController) {
        val sideNavView : NavigationView = findViewById(R.id.menu_navigation)
        sideNavView.setupWithNavController(navController)
        sideNavView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            // Handle navigation view item clicks here.
            when (menuItem.itemId) {
                R.id.drawerMenu1 -> {
//                    navController.navigate(R.id.firstMenuFragment)
                }
                R.id.drawerMenu2 -> {
//                    navController.navigate(R.id.secondMenuFragment)
                }
                R.id.drawerMenu3 -> {
//                    navController.navigate(R.id.thirdMenuFragment)
                }
                R.id.genre_action -> {
//                    navController.navigate(R.id.firstMenuFragment)
                }
                R.id.genre_adventure -> {
//                    navController.navigate(R.id.secondMenuFragment)
                }
                R.id.genre_comedy -> {
//                    navController.navigate(R.id.thirdMenuFragment)
                }
                R.id.genre_drama -> {
//                    navController.navigate(R.id.firstMenuFragment)
                }
                R.id.genre_fantasy -> {
//                    navController.navigate(R.id.secondMenuFragment)
                }
                R.id.genre_romance -> {
//                    navController.navigate(R.id.thirdMenuFragment)
                }
                R.id.genre_scifi -> {
//                    navController.navigate(R.id.firstMenuFragment)
                }
                R.id.drawer_favorite -> {
                    navController.navigate(R.id.favoriteFragment)
                }
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupDrawerHeader(navView: NavigationView) {
    }

    private fun loadAvatar(){
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