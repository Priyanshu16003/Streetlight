package com.bhaskar.streetlight.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "complaint_table")
data class ComplaintEntity(

    @PrimaryKey(autoGenerate = true)
    var complaintId: Int = 0,

//    @ColumnInfo(name = "userId")
//    var userId: Int,

    @ColumnInfo(name = "mobile_number")
    var mobileNumber: Long,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "user_name")
    var pole: Int,

    @ColumnInfo(name = "complaint_description")
    var complaintDescription: String,

//    @ColumnInfo(name = "image_url")
//    var imageUrl: String

)
