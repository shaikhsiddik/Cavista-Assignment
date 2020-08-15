package com.cavistatest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.axxess.cavistaassignment.model.ServerException
import com.axxess.cavistaassignment.model.AppBean
import com.axxess.cavistaassignment.model.LoaderModel
import com.axxess.cavistaassignment.model.PagerProgressModel
import com.axxess.cavistaassignment.repositry.NetworkRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private var searchItems = MutableLiveData<ArrayList<AppBean>>()
    private var errorReponse = MutableLiveData<ServerException>()
    var loader = MutableLiveData<LoaderModel>()
    val mResponse = MediatorLiveData<Any>()

    init {
        with(mResponse) {
            addSource(searchItems, Observer {
                postValue(it)
            })
            addSource(errorReponse, Observer {
                postValue(it)
            })
        }
    }

    fun search(page: Int, item: String) {
        viewModelScope.launch(errorHandler) {
            loader.postValue(LoaderModel(true));
            val networkResponse = NetworkRepository.searchImage(page, item)
            if (networkResponse.isSuccessful) {
                val items = ArrayList<AppBean>()
                if (!networkResponse.body()?.data.isNullOrEmpty()) {
                    items.addAll(networkResponse.body()?.data!!)
                    items.add(PagerProgressModel())
                }
                loader.postValue(LoaderModel(false));
                searchItems.postValue(items)

            }
        }
    }

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("error", exception.toString())
        errorReponse.postValue(ServerException(exception.toString()))
        loader.postValue(LoaderModel(false));


    }


}