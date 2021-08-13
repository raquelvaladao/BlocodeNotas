package com.raquel.blocodenotas.data

import androidx.lifecycle.LiveData
import com.raquel.blocodenotas.viewmodel.UserDao

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()
    val sortByHigh: LiveData<List<User>> = userDao.sortByHigh()
    val sortByLow: LiveData<List<User>> = userDao.sortByLow()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
    userDao.deleteUser(user)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }

    fun sortByHigh(): LiveData<List<User>>{
        return userDao.sortByHigh()
    }

    fun sortByLow(): LiveData<List<User>>{
        return userDao.sortByLow()
    }

}