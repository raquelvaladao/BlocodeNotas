package com.raquel.blocodenotas.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raquel.blocodenotas.data.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_table ORDER BY CASE WHEN notesPriorityDB LIKE 'H%' THEN 1 WHEN notesPriorityDB LIKE 'M%' THEN 2 WHEN notesPriorityDB LIKE 'L%' THEN 3 END")
    fun sortByHigh(): LiveData<List<User>>

    @Query("SELECT * FROM user_table ORDER BY CASE WHEN notesPriorityDB LIKE 'L%' THEN 1 WHEN notesPriorityDB LIKE 'M%' THEN 2 WHEN notesPriorityDB LIKE 'H%' THEN 3 END")
    fun sortByLow(): LiveData<List<User>>

}