package com.example.goaltracker.Model

import com.example.goaltracker.Priority

data class Habit(
    var title: String,
    var description: String,
    var progress: Int,
    var priority: Priority,
    var timeStamp: String

)
{
}