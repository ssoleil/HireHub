package com.example.hirehub.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.hirehub.model.dao.UserDao
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allUsers: Flow<List<User>> = userDao.getAllUser()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

     fun findUser(username: String, pwd: String) : LiveData<User?> =
        userDao.findUserByUsernameAndPwd(username, pwd)


}