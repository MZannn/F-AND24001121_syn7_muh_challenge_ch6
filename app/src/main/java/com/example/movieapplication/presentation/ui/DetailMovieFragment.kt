package com.example.movieapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapplication.api.ApiClient
import com.example.movieapplication.data.remote.ApiService
import com.example.movieapplication.databinding.FragmentDetailMovieBinding
import com.example.movieapplication.domain.model.response.MovieDetailResponse
import com.example.movieapplication.presentation.viewModel.MovieViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailMovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentDetailMovieBinding
    private val args: DetailMovieFragmentArgs by navArgs()
    private val viewModel: MovieViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(layoutInflater, container, false)
        viewModel.movieDetailResponse.observe(viewLifecycleOwner, Observer{
            it?.let {
                binding.tvTitle.text = it.title
                binding.tvOverview.text = it.overview
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                    .into(binding.imageDetailMovie)
                binding.imageDetailMovie.background = null
            }
        })
        viewModel.getMovieDetail(args.id)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailMovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailMovieFragment().apply {
            }
    }
}