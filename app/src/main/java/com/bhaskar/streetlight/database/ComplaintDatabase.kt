package com.bhaskar.streetlight.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ComplaintEntity::class], version = 1, exportSchema = false)
abstract class ComplaintDatabase : RoomDatabase() {

    abstract val complaintDatabaseDao: ComplaintDAO

    companion object {

        @Volatile
        private var INSTANCE: ComplaintDatabase? = null


        fun getInstance(context: Context): ComplaintDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ComplaintDatabase::class.java,
                        "complaint_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
