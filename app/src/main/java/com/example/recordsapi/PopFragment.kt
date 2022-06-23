package com.example.recordsapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recordsapi.adapter.SongAdapter
import com.example.recordsapi.databinding.FragmentPopBinding
import com.example.recordsapi.domain.DomainSongs
import com.example.recordsapi.presenters.AllPopSongsPresenterContract
import com.example.recordsapi.presenters.AllPopSongsViewContract
import com.example.recordsapi.presenters.AllRockSongsPresenterContract
import com.example.recordsapi.presenters.AllRockSongsViewContract
import javax.inject.Inject


class PopFragment : Fragment(), AllPopSongsViewContract {


    val binding by lazy {
        FragmentPopBinding.inflate(layoutInflater)
    }
    private val songAdapter by lazy {
        SongAdapter {

        }
    }

    @Inject
    lateinit var popSongsPresenter: AllPopSongsPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SongsApp.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        popSongsPresenter.init(this)
        popSongsPresenter.registerToNetworkState()
        popSongsPresenter.getPopSongs()
        popSongsPresenter.destroyPresenter()

        binding.fragmentRecyclerPop.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = songAdapter
            return binding.root
        }


    }


    override fun loadingSongs(isLoading: Boolean) {
        Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
    }

    override fun successSongsResponse(songs: List<DomainSongs>, isOffLine: Boolean) {
        Toast.makeText(requireContext(), "this is the data...", Toast.LENGTH_LONG).show()
    }

    override fun error(error: Throwable) {
        Toast.makeText(requireContext(), "ERROR!", Toast.LENGTH_LONG).show()
    }
}


