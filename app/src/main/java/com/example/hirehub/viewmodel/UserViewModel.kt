package com.example.hirehub.model

import android.util.Log
import androidx.lifecycle.*
import com.example.hirehub.model.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = userRepository.allUsers.asLiveData()
    //val returnedUser = MutableLiveData<User?>()

//    suspend fun findUser(username: String, pwd: String) = viewModelScope.async {
//        userRepository.findUser(username, pwd)
//    }.await()

//    fun get_name_px(phonex: String) = viewModelScope.async {
//        repository.get_name_px(phonex)
//    }.await()

        //todo: remember the user
        //val currentUser: User

        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         */
        fun insert(user: User) = viewModelScope.launch {
            userRepository.insert(user)
        }

//    fun findbyID(id: Int): LiveData</*your data type*/> {
//        val result = MutableLiveData</*your data type*/>()
//        viewModelScope.launch {
//            val returnedrepo = repo.delete(id)
//            result.postValue(returnedrepo)
//        }
//        return result.
//    }

    fun findUser(username: String, pwd: String) : LiveData<User?> {
        return userRepository.findUser(username, pwd)


//        CoroutineScope(Dispatchers.IO).launch {
//            val queryResult = userRepository.findUser(username, pwd)
//            Log.d("UserViewModel", queryResult?.userName ?: "no")
//            user.postValue(queryResult)
//        }
////        val message : String = user.value?.userUsername ?: "no"
//        Log.d("UserViewModel", user.value?.userName ?: "no")
//        return user
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