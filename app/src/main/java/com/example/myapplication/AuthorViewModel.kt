package com.example.myapplication

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class AuthorViewModel(application: Application) : ViewModel() {
    val authorList: LiveData<List<AuthorEntity>>
    private val repository: AuthorRepository

    var id by mutableStateOf(0)
    var name by mutableStateOf("")

    init {
        Dependencies.init(application)
        val authorDb = Dependencies.appDatabase
        val authorDao = authorDb.getBookDao()
        repository = AuthorRepository(authorDao)
        authorList = repository.authorList
    }

    fun changeName(value: String){
        name = value
    }

    fun changeId(value: String){
        id = value.toIntOrNull()?:id
    }

    fun addAuthor(){
        repository.insertNewAuthor(AuthorEntity(id, name))
    }

    fun deleteAuthor(id: Int){
        repository.removeAuthorById(id)
    }

}