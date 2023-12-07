package com.example.vocabulary_book.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vocabulary_book.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM user_table")
    fun getAllUser() : Flow<List<UserEntity>>

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("SELECT COUNT(*) FROM user_table WHERE email = :email")
    fun isEmailExists(email: String) : Int
}