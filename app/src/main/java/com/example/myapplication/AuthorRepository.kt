package com.example.myapplication

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorRepository (
    private val iDao: IDao
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertNewAuthor(authorEntity: AuthorEntity){
        coroutineScope.launch(Dispatchers.IO){
            iDao.insertNewAuthorData(authorEntity)
        }
    }

    val authorList: LiveData<List<AuthorEntity>> = iDao.getAllAuthorData()

//    fun getAllBooks(): LiveData<List<BookInfoTuple>> {
//        return coroutineScope.launch(Dispatchers.IO){
//            return@withContext iDao.getAllBookData()
//        }
//    }

    fun removeAuthorById(id: Int){
        coroutineScope.launch(Dispatchers.IO) {
            iDao.deleteAuthorDataById(id)
        }
    }
}
