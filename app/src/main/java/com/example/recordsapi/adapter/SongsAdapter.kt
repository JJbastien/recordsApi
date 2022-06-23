package com.example.recordsapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recordsapi.R
import com.example.recordsapi.databinding.FragmentDetailsSongsBinding
import com.example.recordsapi.domain.DomainSongs
import com.example.recordsapi.model.Songs
import com.squareup.picasso.Picasso


class SongAdapter(
    private val songsData: MutableList<DomainSongs> = mutableListOf(),
    private val onSongsClicked: (DomainSongs) -> Unit)
    :  RecyclerView.Adapter< SongAdapter.MyViewHolder>(){

    fun updateNewSongs(newSongs: List<DomainSongs>) {
        songsData.clear()
        songsData.addAll(newSongs)
        notifyDataSetChanged()}

    class MyViewHolder(

        private val binding : FragmentDetailsSongsBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(songs: DomainSongs, onSongsHandler: (DomainSongs) -> Unit){
            binding.artistName.text = songs.artistName
            binding.collectionName.text = songs.collectionName
            binding.price.text= songs.trackPrice.toString()
            Picasso.get()
                .load(songs.artworkUrl60)
                .placeholder(R.drawable.ic_baseline_picture_in_picture_24)
                .error(R.drawable.ic_baseline_picture_in_picture_24)
                .fit()
                .into(binding.image)
        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
                FragmentDetailsSongsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
            )


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(songsData[position], onSongsClicked)

    }

    override fun getItemCount(): Int {
       return songsData.size
    }
}


