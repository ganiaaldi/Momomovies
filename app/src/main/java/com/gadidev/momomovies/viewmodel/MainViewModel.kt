package com.gadidev.momomovies.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gadidev.momomovies.model.Movies
import com.gadidev.momomovies.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MoviesRepository,
                                        private val savedStateHandle: SavedStateHandle
) : ViewModel(),LifecycleObserver {

    val errorMessage = MutableLiveData<String>()
//    val movieList = MutableLiveData<List<Movies>>()
    var job: Job? = null
//    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        onError("Exception handled: ${throwable.localizedMessage}")
//    }
    val loading = MutableLiveData<Boolean>()
    val movies = repository.getComingSoon()

    fun getComingSoon() {
        job = CoroutineScope(Dispatchers.IO).launch {
          val movies = repository.getComingSoon()
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    movieList.postValue(response.body())
//                    loading.value = false
//                } else {
//                    onError("Error : ${response.message()} ")
//                }
//            }
        }

    }

//    private fun onError(message: String) {
//        errorMessage.value = message
//        loading.value = false
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }

}