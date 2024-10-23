package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "book",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_author"]
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_genre"]
        )
    ]
)

data class BookEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name="id_author") val idAuthor: Int,
    @ColumnInfo(name="id_genre") val idGenre: Int,
    @ColumnInfo() val title: String,
    @ColumnInfo() val year: Int
)
