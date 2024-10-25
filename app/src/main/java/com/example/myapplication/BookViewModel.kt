package com.example.myapplication

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BookViewModel(application: Application) : ViewModel() {
    val bookList: LiveData<List<BookInfoTuple>>
    private val repository: BookRepository

    var id by mutableStateOf(0)
    var idAuthor by mutableStateOf(0)
    var idGenre by mutableStateOf(0)
    var title by mutableStateOf("")
    var year by mutableStateOf(0)

    init {
        Dependencies.init(application)
        val bookDb = Dependencies.appDatabase
        val bookDao = bookDb.getBookDao()
        repository = BookRepository(bookDao)
        bookList = repository.bookList
    }

    fun changeIdGenre(value: String){
        idGenre = value.toIntOrNull()?:idGenre
    }

    fun changeIdAuthor(value: String){
        idAuthor = value.toIntOrNull()?:idAuthor
    }

    fun changeTitle(value: String){
        title = value
    }

    fun changeYear(value: String){
        year = value.toIntOrNull()?:year
    }

    fun addBook(){
        repository.insertNewBook(BookEntity(id, idAuthor, idGenre, title, year))
    }

    fun deleteBook(id: Int){
        repository.removeBookById(id)
    }

}