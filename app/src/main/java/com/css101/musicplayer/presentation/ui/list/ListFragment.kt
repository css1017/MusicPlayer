package com.css101.musicplayer.presentation.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.css101.musicplayer.presentation.base.BaseFragment
import com.css101.musicplayer.databinding.FragmentListBinding
import com.css101.musicplayer.domain.models.AudioFile
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val vm: ListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getMusicList()

        vm.musicList.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter(musicList: List<AudioFile>) =with(binding) {
        val adapter = ListAdapter(musicList) {}
        rvList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}