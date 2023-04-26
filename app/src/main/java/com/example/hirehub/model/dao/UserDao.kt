package com.example.hirehub.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllUser(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Query("SELECT * FROM user_table WHERE user_username LIKE :name AND user_pwd LIKE :pwd")
    fun findUserByUsernameAndPwd(
        name: String, pwd: String): LiveData<User?>

    @Query("SELECT * FROM user_table WHERE user_username LIKE :name")
    fun findUserByUsername(name: String): LiveData<User?>

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}