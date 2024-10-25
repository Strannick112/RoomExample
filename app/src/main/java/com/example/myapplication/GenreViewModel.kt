package com.example.myapplication

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class GenreViewModel (application: Application) : ViewModel() {
    val genreList: LiveData<List<GenreEntity>>
    private val repository: GenreRepository

    var id by mutableStateOf(0)
    var type by mutableStateOf("")

    init {
        Dependencies.init(application)
        val genreDb = Dependencies.appDatabase
        val genreDao = genreDb.getBookDao()
        repository = GenreRepository(genreDao)
        genreList = repository.genreList
    }

    fun changeType(value: String){
        type = value
    }

    fun changeId(value: String){
        id = value.toIntOrNull()?:id
    }

    fun addGenre(){
        repository.insertNewGenre(GenreEntity(id, type))
    }

    fun deleteGenre(id: Int){
        repository.removeGenreById(id)
    }

}