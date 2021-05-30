package com.raquel.blocodenotas.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope


class UserViewModel(application: Application) {
    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

   // suspend fun addUser(user: User){
   //viewModelScope.launch(Dispatchers.IO){
    //    repository.addUser(user) }}
}