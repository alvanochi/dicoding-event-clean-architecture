package com.alvan.submissionexpert.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvan.submissionexpert.R
import com.alvan.submissionexpert.core.data.Resource
import com.alvan.submissionexpert.ui.adapter.EventAdapter
import com.alvan.submissionexpert.databinding.ActivityMainBinding
import com.alvan.submissionexpert.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = getString(R.string.app_name)


        val eventAdapter = EventAdapter()
        eventAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }


        mainViewModel.event.observe(this) {event ->
            if (event != null){
                when(event) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        eventAdapter.submitList(event.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvEmpty.visibility = View.VISIBLE
                    }
                }
            }
        }

        with(binding.rvEvent) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = eventAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_fav -> {
                startActivity(Intent(this, Class.forName("com.alvan.submissionexpert.favorite.FavoriteActivity")))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}