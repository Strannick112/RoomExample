package com.example.myapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(
    private val iDao: IDao
) {
    suspend fun insertNewBook(bookEntity: BookEntity){
        withContext(Dispatchers.IO){
            iDao.insertNewBookData(bookEntity)
        }
    }

    suspend fun getAllBooks(): List<BookInfoTuple>{
        return withContext(Dispatchers.IO){
            return@withContext iDao.getAllBookData()
        }
    }

    suspend fun removeBookById(id: Int){
        withContext(Dispatchers.IO){}
        iDao.deleteBookDataById(id)
    }
}