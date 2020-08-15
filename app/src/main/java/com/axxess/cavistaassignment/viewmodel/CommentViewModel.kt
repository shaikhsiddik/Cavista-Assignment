package com.cavistatest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.axxess.cavistaassignment.model.Comment

import com.axxess.cavistaassignment.repositry.DataBaseRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application) {

    var comment = MutableLiveData<Comment>()


    fun saveComment(comment: Comment) {
        viewModelScope.launch(errorHandler) {
            DataBaseRepository.saveComment(getApplication(), comment);
        }
    }

    fun getComment(id: String) {
        viewModelScope.launch(errorHandler) {
            val res = DataBaseRepository.getComment(getApplication(), id)
            comment.postValue(res) ;

        }
    }

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("error", exception.toString())

    }


}