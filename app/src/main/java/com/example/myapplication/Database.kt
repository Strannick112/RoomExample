package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        AuthorEntity::class,
        GenreEntity::class,
        BookEntity::class
    ]
)

abstract class Database: RoomDatabase() {
    abstract fun getBookDao(): IDao


}
