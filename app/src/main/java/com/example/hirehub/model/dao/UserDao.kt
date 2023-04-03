package com.example.hirehub.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAllUser(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    //login
    //returns either one existing User ot null
    @Query("SELECT * FROM user_table WHERE user_username LIKE :name AND user_pwd LIKE :pwd")
    fun findUserByUsernameAndPwd(name: String, pwd: String): LiveData<User?>

    //register check
    //user_username is unique per a person
    //returns either one existing User ot null
    @Query("SELECT * FROM user_table WHERE user_username LIKE :name")
    fun findUserByUsername(name: String): LiveData<User?>

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

}