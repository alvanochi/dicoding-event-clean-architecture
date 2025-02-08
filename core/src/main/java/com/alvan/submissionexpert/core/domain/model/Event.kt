package com.alvan.submissionexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val mediaCover: String,
    val registrants: Int,
    val link: String,
    val description: String,
    val quota: Int,
    val name: String,
    val id: Int,
    val beginTime: String,
    val isFavorite: Boolean
): Parcelable