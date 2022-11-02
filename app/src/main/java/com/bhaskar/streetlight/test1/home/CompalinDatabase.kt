package com.bhaskar.streetlight.test1.home

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bhaskar.streetlight.database.ComplaintDAO
import com.bhaskar.streetlight.database.ComplaintEntity

@Database(entities = [ComplaintEntity::class], version = 1, exportSchema = false)
abstract class ComplainDatabase : RoomDatabase() {

    abstract fun complaintDao(): ComplaintDAO
}