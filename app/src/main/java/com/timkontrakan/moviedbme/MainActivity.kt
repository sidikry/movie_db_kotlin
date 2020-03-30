package com.timkontrakan.moviedbme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager

    private var popularMoviePage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularMovies = findViewById(R.id.popular_movies)
        popularMoviesLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MoviesAdapter(mutableListOf())
        popularMovies.adapter = popularMoviesAdapter

        getPopularMovies()
    }

    private fun getPopularMovies(){
        MoviesRepository.getPopularMovies(
            popularMoviePage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun onPopularMoviesFetched(movies: List<Movie>){
        popularMoviesAdapter.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }

    private fun attachPopularMoviesOnScrollListener() {
        popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                18
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies.removeOnScrollListener(this)
                    popularMoviePage++
                    getPopularMovies()
                }
            }
        })

    }

    private fun onError(){
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }


}
