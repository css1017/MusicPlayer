package com.css101.musicplayer.presentation.ui.player

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.css101.musicplayer.R
import com.css101.musicplayer.presentation.base.BaseFragment
import com.css101.musicplayer.databinding.FragmentPlayerBinding
import com.css101.musicplayer.domain.models.AudioFile

class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private val args: PlayerFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAudio(args.audioFile)

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
}