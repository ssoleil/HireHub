package com.example.hirehub.model

import androidx.lifecycle.*
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = userRepository.allUsers.asLiveData()

        //todo: remember the user
        //val currentUser: User

        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         */
        fun insert(user: User) = viewModelScope.launch {
            userRepository.insert(user)
        }

    fun findUser(username: String, pwd: String) : LiveData<User?> {
        return userRepository.findUser(username, pwd)
    }

    fun findUser(username: String) : LiveData<User?> {
        return userRepository.findUser(username)
    }
}

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}