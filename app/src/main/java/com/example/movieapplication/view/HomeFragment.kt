package com.example.movieapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.adapter.MovieAdapter
import com.example.movieapplication.api.ApiClient
import com.example.movieapplication.databinding.FragmentHomeBinding
import com.example.movieapplication.model.response.MovieResponse
import com.example.movieapplication.repository.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val userRepository: UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            userRepository.userFlow.collect { user ->
                user?.let {
                    binding.welcomeUsername.text = "Welcome, ${it.username}!"
                }
            }
        }

        binding.ibtnProfile.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
            findNavController().navigate(action)
        }

        val recyclerView = binding.rvMovies
        ApiClient.instance.getMovieNowPlaying().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val json = Gson().toJson(response.body())
                    val data = Gson().fromJson(json, MovieResponse::class.java)

                    val adapter = MovieAdapter(data.results) { movie ->
                        Log.d("HomeFragment", "Movie clicked: ${movie.title}")
                        val action = HomeFragmentDirections
                            .actionHomeFragmentToDetailMovieFragment(movie.id.toString())
                        findNavController().navigate(action)
                    }
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = adapter

                } else {
                    Log.e("HomeFragment", "Response is not successful: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("SIMPLE_TAG", "error ${t.message}")
            }

        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}
