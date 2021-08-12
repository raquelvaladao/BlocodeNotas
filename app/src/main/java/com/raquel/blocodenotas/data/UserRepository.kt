package com.raquel.blocodenotas.data

import androidx.lifecycle.LiveData
import com.raquel.blocodenotas.viewmodel.UserDao

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }


    //suspend fun deleteUser(user: User){
     //   userDao.deleteUser(user)
    //}
}