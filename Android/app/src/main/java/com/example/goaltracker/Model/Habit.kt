package com.example.goaltracker.Model

import androidx.room.*

@Entity(tableName = "habit_table")
data class Habit(
    var title: String?,
    var description: String?,
    var progress: Int,
    var priority: String?,
    var goalId: Int,
    var mentorId: Int,
//    var timeStamp: HabitTimePeriod?,
    @Embedded(prefix = "habitTime_")
    var habitTimePeriod: HabitTimePeriod

//    var mentor: User
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

//    @PrimaryKey var habitId: Int = 0
//
//    @ColumnInfo(index = true)
//    var goalOwnerId: Int = 0
}

