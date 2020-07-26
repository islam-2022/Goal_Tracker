package com.example.goaltracker.Model

import com.example.goaltracker.HabitTimePeriod

data class Habit(
    var title: String?,
    var description: String?,
    var progress: Int,
    var priority: Any?,
    var timeStamp: HabitTimePeriod?
//    var user: User
)
{
}