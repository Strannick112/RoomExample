package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IDao {
// Книги
    @Insert(entity = BookEntity::class)
    fun insertNewBookData(bookEntity: BookEntity)

    @Query("SELECT book.id as id, author.name as author, genre.type as genre, book.title as title, book.year as year\n" +
            "FROM book\n" +
            "INNER JOIN author ON author.id = book.id_author\n" +
            "INNER JOIN genre ON genre.id = book.id_genre")
    fun getAllBookData(): LiveData<List<BookInfoTuple>>

    @Query("DELETE FROM book WHERE id = :bookId")
    fun deleteBookDataById(bookId: Int)

// Авторы
    @Insert(entity = AuthorEntity::class)
    fun insertNewAuthorData(authorEntity: AuthorEntity)

    @Query("SELECT id, name\n" +
            "FROM author")
    fun getAllAuthorData(): LiveData<List<AuthorEntity>>

    @Query("DELETE FROM author WHERE id = :authorId")
    fun deleteAuthorDataById(authorId: Int)

// Жанры

    @Insert(entity = GenreEntity::class)
    fun insertNewGenreData(genreEntity: GenreEntity)

    @Query("SELECT id, type\n" +
            "FROM genre")
    fun getAllGenreData(): LiveData<List<GenreEntity>>

    @Query("DELETE FROM genre WHERE id = :genreId")
    fun deleteGenreDataById(genreId: Int)
}
