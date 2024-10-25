package com.example.myapplication

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenreRepository (
    private val iDao: IDao
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertNewGenre(genreEntity: GenreEntity){
        coroutineScope.launch(Dispatchers.IO){
            iDao.insertNewGenreData(genreEntity)
        }
    }

    val genreList: LiveData<List<GenreEntity>> = iDao.getAllGenreData()

//    fun getAllBooks(): LiveData<List<BookInfoTuple>> {
//        return coroutineScope.launch(Dispatchers.IO){
//            return@withContext iDao.getAllBookData()
//        }
//    }

    fun removeGenreById(id: Int){
        coroutineScope.launch(Dispatchers.IO) {
            iDao.deleteGenreDataById(id)
        }
    }
}