package com.alvan.submissionexpert.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alvan.submissionexpert.core.data.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getAllEvent(): Flow<List<EventEntity>>

    @Query("SELECT * FROM event where isFavorite = 1")
    fun getFavoriteEvent(): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: List<EventEntity>)

    @Update
    fun updateFavoriteEvent(event: EventEntity)

}