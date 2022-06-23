package com.example.recordsapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recordsapi.adapter.SongAdapter
import com.example.recordsapi.databinding.FragmentClassicBinding
import com.example.recordsapi.domain.DomainSongs
import com.example.recordsapi.presenters.AllClassicSongsPresenterContract
import com.example.recordsapi.presenters.AllClassicSongsViewContract
import javax.inject.Inject


class ClassicFragment : Fragment(), AllClassicSongsViewContract {
    private val binding by lazy {
        FragmentClassicBinding.inflate(layoutInflater)
    }
    private val songAdapter by lazy {
        SongAdapter {

        }
    }

    @Inject
    lateinit var classicSongsPresenter: AllClassicSongsPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SongsApp.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        classicSongsPresenter.init(this)
        classicSongsPresenter.registerToNetworkState()
        classicSongsPresenter.getClassicSongs()
        classicSongsPresenter.destroyPresenter()

        binding.fragmentRecyclerClassic.apply {
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





