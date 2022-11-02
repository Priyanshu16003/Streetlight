package com.bhaskar.streetlight.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ComplaintDAO {

    @Insert
    suspend fun insert(complaint: ComplaintEntity)

//    @Delete
//    suspend  fun deleteComplaint(complaint: ComplaintEntity):Int

    @Query("SELECT * FROM complaint_table")
    fun getAllComplaints(): LiveData<List<ComplaintEntity>>

//    @Query("DELETE FROM Register_users_table")
//    suspend fun deleteAll(): Int

//    @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
//    suspend fun getUsername(userName: String): RegisterEntity?

}