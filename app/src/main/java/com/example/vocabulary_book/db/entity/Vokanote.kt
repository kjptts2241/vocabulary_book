package com.example.vocabulary_book.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "vokanote_table",
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class Vokanote (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int?, // Primary Key

    @ColumnInfo(name = "userForeignKey")
    var userId: Int = 0, // User 외래키

    @ColumnInfo(name = "language")
    var language : String, // 언어

    @ColumnInfo(name = "nickname")
    var title : String // 제목
)