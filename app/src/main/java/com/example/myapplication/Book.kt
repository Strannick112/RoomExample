package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Book(
    val id: Int,
    val idAuthor: Int,
    val idGenre: Int,
    val title: String,
    val year: Int
) {
    fun toBookEntity(): BookEntity{
        return BookEntity(
            id = 0,
            idAuthor = idAuthor,
            idGenre = idGenre,
            title = title,
            year = year
        )
    }
}
