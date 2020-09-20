package com.example.goaltracker.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "evidence_table")
data class Evidence(
    var author: String,
//    var mentorId: Int,
    var habitId: Int?,
    var context: String?,
    var image: String?,
    @SerializedName("time")
    var timeStamp: String?,
    var seen: Boolean
): Serializable{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
