package com.example.goaltracker

data class HabitTimePeriod(
    var timeFrequency: Int,
    var isFrequencyPerWeek: Boolean,
    var Days: ArrayList<Days>,
    var habitEnds: String?,
    var habitEndsAtDate: String?,
    var habitEndsAfter: Int
) {
}