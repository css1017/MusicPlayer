package com.css101.musicplayer.presentation.ui.player

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.css101.musicplayer.R
import com.css101.musicplayer.presentation.base.BaseFragment
import com.css101.musicplayer.databinding.FragmentPlayerBinding
import com.css101.musicplayer.domain.models.AudioFile
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private val args: PlayerFragmentArgs by navArgs()
    private val vm: PlayerViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.saveAudio(args.audioFile)
        showEmpty()
        setObservers()
    }

    private fun setObservers(){
        vm.audioFile.observe(viewLifecycleOwner) {
            showPlayer()
            setAudio(it)
        }
        vm.isPlaying.observe(viewLifecycleOwner) {
            setControls(it)
        }
    }

    private fun setControls(isPlaying: Boolean) = with(binding) {
        btnPlayPause.setOnClickListener {
            vm.playPause()
        }
        when (isPlaying) {
            false -> btnPlayPause.setImageResource(R.drawable.ic_play)
            true -> btnPlayPause.setImageResource(R.drawable.ic_pause)
        }
    }

    private fun setAudio(audio: AudioFile?) = with(binding) {
        Glide.with(requireContext())
            .load(audio?.coverUri)
            .placeholder(R.drawable.img_no_album)
            .error(R.drawable.img_no_album)
            .into(ivCover)
        tvArtistPlayer.text = audio?.artist
        tvTitlePlayer.text = audio?.title
    }

    private fun showEmpty() = with(binding) {
        btnPickSong.setOnClickListener {
            val action = PlayerFragmentDirections.actionPlayerToList()
            navController.navigate(action)
        }
        llEmpty.visibility = View.VISIBLE
        ivCover.visibility = View.GONE
        llTitle.visibility = View.GONE
        btnPlayPause.visibility = View.GONE
    }

    private fun showPlayer() = with(binding) {
        llEmpty.visibility = View.GONE
        ivCover.visibility = View.VISIBLE
        llTitle.visibility = View.VISIBLE
        btnPlayPause.visibility = View.VISIBLE
    }
}