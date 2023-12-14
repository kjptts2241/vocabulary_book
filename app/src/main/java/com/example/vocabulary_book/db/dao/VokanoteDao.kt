package com.example.vocabulary_book.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vocabulary_book.db.entity.User
import com.example.vocabulary_book.db.entity.Vokanote

@Dao
interface VokanoteDao {

    @Query("SELECT * FROM vokanote_table")
    fun getVokanoteAll(): List<Vokanote>

    @Query("SELECT COUNT(*) FROM vokanote_table")
    fun getVokanoteCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVokanote(vokanote: Vokanote)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}