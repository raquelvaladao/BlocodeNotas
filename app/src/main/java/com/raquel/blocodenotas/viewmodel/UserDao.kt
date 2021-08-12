package com.raquel.blocodenotas.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raquel.blocodenotas.data.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

 //   @Delete
  //  suspend fun deleteUser(user: User)

}