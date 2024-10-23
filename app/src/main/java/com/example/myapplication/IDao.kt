package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IDao {
    @Insert(entity = BookEntity::class)
    fun insertNewBookData(bookEntity: BookEntity)

    @Query("SELECT book.id, author.name, genre.type, book.title, book.year\n" +
            "FROM book\n" +
            "INNER JOIN author ON author.id = book.id_author\n" +
            "INNER JOIN genre ON genre.id = book.id_genre")
    fun getAllBookData(): List<BookInfoTuple>

    @Query("DELETE FROM book WHERE id = :bookId")
    fun deleteBookDataById(bookId: Int)
}
