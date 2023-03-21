package com.example.hirehub.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAllUser(): List<User>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

}