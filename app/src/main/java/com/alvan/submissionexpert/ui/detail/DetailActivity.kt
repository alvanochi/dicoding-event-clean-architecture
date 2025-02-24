package com.alvan.submissionexpert.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.text.HtmlCompat
import com.alvan.submissionexpert.R
import com.alvan.submissionexpert.core.domain.model.Event
import com.alvan.submissionexpert.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailEvent = getParcelableExtra(intent, EXTRA_DATA, Event::class.java)
        showDetailEvent(detailEvent)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailEvent(detailEvent: Event?) {
        detailEvent?.let {
            with(binding) {
                ivDetail.loadImage(url = detailEvent.mediaCover)
                tvDetailTitle.text = detailEvent.name
                tvDetailQuota.text = " ${detailEvent.registrants.let { detailEvent.quota.minus(it) }}"
                tvDetailDate.text = detailEvent.beginTime
                tvDetailDesc.text = HtmlCompat.fromHtml(
                    detailEvent.description,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                btnRegister.isEnabled = detailEvent.registrants.let { detailEvent.quota.minus(it) } != 0
                btnRegister.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(detailEvent.link)
                    }
                    startActivity(intent)
                }

                var statusFavorite = detailEvent.isFavorite
                setStatusFavorite(statusFavorite)

                btnFav.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteEvent(detailEvent, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.btnFav.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (statusFavorite) R.drawable.ic_fav_filled else R.drawable.ic_fav_border
            )
        )
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this@DetailActivity)
            .load(url)
            .into(this)
    }
}