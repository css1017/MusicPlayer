package com.css101.musicplayer.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCaller
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.css101.musicplayer.presentation.utils.PermissionsHelper
import org.koin.core.component.KoinComponent

abstract class BaseFragment<VB: ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(), KoinComponent,
    ActivityResultCaller {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected lateinit var navController: NavController
    protected lateinit var permissionsHelper: PermissionsHelper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        permissionsHelper = PermissionsHelper(this)

        navController = findNavController()
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }
}