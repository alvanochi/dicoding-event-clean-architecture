package com.alvan.submissionexpert.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "event")
@Parcelize
data class EventEntity(

    @ColumnInfo(name = "mediaCover")
    val mediaCover: String,

    @ColumnInfo(name = "registrants")
    val registrants: Int,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "quota")
    val quota: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "beginTime")
    val beginTime: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable