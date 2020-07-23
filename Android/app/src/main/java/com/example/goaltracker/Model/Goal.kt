package com.example.goaltracker.Model

data class Goal(
    var title: String,
    var progress: Int,
    var habits: List<Habit>,
    var timeStemp: String
){




}