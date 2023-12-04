package com.example.vocabulary_book

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * from user ORDER BY id DESC")
    fun getAll() : List<UserData>

    @Query("SELECT COUNT(*) from user WHERE email = :email")
    fun isEmailExists(email : String?) : Int

    @Insert
    fun insert(user : UserData?)

    @Delete
    fun delete(user : UserData)

    @Update
    fun update(user : UserData)
}