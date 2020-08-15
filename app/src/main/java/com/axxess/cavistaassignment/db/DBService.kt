package com.axxess.cavistaassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axxess.cavistaassignment.model.Comment


@Dao
interface DBService {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComment(result: Comment);

    @Query(value = "SELECT * FROM Comment WHERE id= :search")
    suspend fun fetchComment(search: String): Comment;
}