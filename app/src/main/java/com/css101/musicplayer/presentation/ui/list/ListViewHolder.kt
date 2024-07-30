package com.css101.musicplayer.presentation.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.css101.musicplayer.R
import com.css101.musicplayer.databinding.ItemListBinding
import com.css101.musicplayer.domain.models.AudioFile


class ListViewHolder(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AudioFile): Unit = with(binding) {
        val ctx = tvTitle.context
        tvTitle.text = item.title
        tvArtist.text = item.artist
        tvLength.text = item.formattedLength
        Glide.with(ctx)
            .load(item.coverUri)
            .placeholder(R.drawable.img_no_album_small)
            .error(R.drawable.img_no_album_small)
            .into(ivIcon)
    }

}