package com.example.goaltracker.Model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_table")
data class Goal(

    var title: String?,
    var progress: Int,
    @PrimaryKey
    var id: Int
//    @Embedded(prefix = "habit_")
//    var habits: ArrayList<Habit> = ArrayList()
){
//@PrimaryKey(autoGenerate = true) var goalId: Int = 0
}