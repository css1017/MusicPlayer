package com.css101.musicplayer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.css101.musicplayer.R
import com.css101.musicplayer.databinding.ActivityMainBinding
import com.css101.musicplayer.presentation.utils.PermissionsHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var permissionsHelper: PermissionsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionsHelper = PermissionsHelper(this@MainActivity)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            permissionsHelper.request(android.Manifest.permission.READ_MEDIA_AUDIO) {}
        } else {
            permissionsHelper.request(android.Manifest.permission.READ_EXTERNAL_STORAGE) {}
        }
    }
}