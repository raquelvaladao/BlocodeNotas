package com.raquel.blocodenotas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.raquel.blocodenotas.data.User
import com.raquel.blocodenotas.data.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    val sortByHigh: LiveData<List<User>>
    val sortByLow: LiveData<List<User>>
    private val repository: UserRepository
    val userDao = UserDatabase.getDatabase(application).userDao()

    init {

        repository = UserRepository(userDao)
        readAllData = repository.readAllData
        sortByHigh = repository.sortByHigh
        sortByLow = repository.sortByLow
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
           repository.deleteUser(user)
      }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun sortByHigh(): LiveData<List<User>> {
        return repository.sortByHigh()
    }
    fun sortByLow(): LiveData<List<User>> {
        return repository.sortByLow()
    }
}