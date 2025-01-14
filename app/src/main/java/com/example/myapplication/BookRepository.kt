package com.example.myapplication

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookRepository(
    private val iDao: IDao
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertNewBook(bookEntity: BookEntity){
        coroutineScope.launch(Dispatchers.IO){
            iDao.insertNewBookData(bookEntity)
        }
    }

    val bookList: LiveData<List<BookInfoTuple>> = iDao.getAllBookData()

//    fun getAllBooks(): LiveData<List<BookInfoTuple>> {
//        return coroutineScope.launch(Dispatchers.IO){
//            return@withContext iDao.getAllBookData()
//        }
//    }

    fun removeBookById(id: Int){
        coroutineScope.launch(Dispatchers.IO) {
            iDao.deleteBookDataById(id)
        }
    }
}
