package com.axxess.cavistaassignment.repositry

import android.content.Context
import com.axxess.cavistaassignment.db.AppDatabase
import com.axxess.cavistaassignment.model.Comment


object DataBaseRepository {

    suspend fun saveComment(context: Context, comment: Comment) {
        return AppDatabase.getDatabase(context).getDBService().saveComment(comment);
    }

    suspend fun getComment(context: Context, id: String): Comment? {
        return AppDatabase.getDatabase(context).getDBService().fetchComment(id);
    }
}