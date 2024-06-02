package com.example.chapter5.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter5.adapter.MoviesAdapter
import com.example.chapter5.api.ApiClient
import com.example.chapter5.databinding.FragmentHomeBinding
import com.example.chapter5.model.response.MovieResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        var recyclerView = binding.rvMovies
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MoviesAdapter(emptyList()) { movie ->

        }
        ApiClient.instance.getMovieNowPlaying().enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    var json = Gson().toJson(response.body())
                    var data = Gson().fromJson(json, MovieResponse::class.java)

                    val adapter = MoviesAdapter(data.results) { movie ->
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

        // Inflate the layout for this fragment
        return binding.root
    }


}