package com.example.vocabulary_book.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vocabulary_book.db.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAll() : List<User>

    // 이메일 존재 여부 확인
    @Query("SELECT COUNT(*) FROM user_table WHERE email = :email")
    fun isEmailExists(email: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}