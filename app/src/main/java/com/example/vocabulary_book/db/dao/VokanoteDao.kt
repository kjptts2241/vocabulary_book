package com.example.vocabulary_book.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vocabulary_book.db.entity.Vokanote

@Dao
interface VokanoteDao {

    @Query("SELECT * FROM vokanote_table")
    fun getAll() : List<Vokanote>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vokanote: Vokanote)

    @Update
    fun update(vokanote: Vokanote)

    @Delete
    fun delete(vokanote: Vokanote)
}