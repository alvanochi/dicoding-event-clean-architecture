package com.alvan.submissionexpert.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvan.submissionexpert.ui.adapter.EventAdapter
import com.alvan.submissionexpert.ui.detail.DetailActivity
import com.alvan.submissionexpert.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favViewModel by viewModel<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.favorite)

        loadKoinModules(favoriteModule)

        val eventAdapter = EventAdapter()
        eventAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favViewModel.favoriteEvent.observe(this){event ->
            eventAdapter.submitList(event)
            binding.tvFavEmpty.visibility = if (event.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavEvent){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = eventAdapter
        }

    }
}