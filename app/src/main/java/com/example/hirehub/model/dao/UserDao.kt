package com.example.hirehub.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAllUser(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    //login
    //returns either one existing User ot null
    @Query("SELECT * FROM user_table WHERE user_username LIKE :name AND user_pwd LIKE :pwd")
    fun findUserByUsernameAndPwd(name: String, pwd: String): LiveData<User?>

    //register check
    //user_username is unique per a person
    //returns either one existing User ot null
    @Query("SELECT * FROM user_table WHERE user_username LIKE :name")
    fun findUserByUsername(name: String): LiveData<User?>

}