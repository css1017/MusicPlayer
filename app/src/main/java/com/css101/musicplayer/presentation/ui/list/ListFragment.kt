package com.css101.musicplayer.presentation.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.css101.musicplayer.presentation.base.BaseFragment
import com.css101.musicplayer.databinding.FragmentListBinding
import com.css101.musicplayer.domain.models.AudioFile
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val vm: ListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        showLoading()
        requestPermission()
        setObservers()
    }

    private fun setObservers() {
        vm.musicList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) showEmpty() else {
                showList()
                setAdapter(it)
            }
        }
    }

    private fun setAdapter(musicList: List<AudioFile>) = with(binding) {
        val adapter = ListSongAdapter(musicList) {
            val action = ListFragmentDirections.actionListToPlayer(it)
            navController.navigate(action)
        }
        rvList.adapter = adapter
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }

    private fun showEmpty() = with(binding) {
        llNoData.visibility = View.VISIBLE
        llNoPermission.visibility = View.GONE
        rvList.visibility = View.GONE
        llLoading.visibility = View.GONE
        btnRefresh.setOnClickListener { vm.getMusicList() }
    }

    private fun showList() = with(binding) {
        llNoData.visibility = View.GONE
        llNoPermission.visibility = View.GONE
        llLoading.visibility = View.GONE
        rvList.visibility = View.VISIBLE
    }

    private fun showNoPermission() = with(binding) {
        llNoData.visibility = View.GONE
        llNoPermission.visibility = View.VISIBLE
        rvList.visibility = View.GONE
        llLoading.visibility = View.GONE
        btnGiveAccess.setOnClickListener { requestPermission() }
        btnGoToSettings.setOnClickListener { permissionsHelper.toAppSettings(requireContext()) }
    }

    private fun showLoading() = with(binding) {
        llNoData.visibility = View.GONE
        llNoPermission.visibility = View.GONE
        rvList.visibility = View.GONE
        llLoading.visibility = View.VISIBLE
    }

    private fun requestPermission() {
        permissionsHelper.request(
            doIfGranted = { vm.getMusicList() },
            doIfNotGranted = { showNoPermission() })
    }
}