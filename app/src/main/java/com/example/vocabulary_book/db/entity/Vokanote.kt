package com.example.vocabulary_book.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vokanote_table")
data class Vokanote (

    @ColumnInfo(name = "language")
    var language : String, // 언어

    @ColumnInfo(name = "nickname")
    var title : String // 제목
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vokanoteId")
    var vokanoteId : Int = 0 // Primary Key
}