package com.example.goaltracker.Model

data class HabitTimePeriod(
    var timeFrequency: Int,
    var isFrequencyPerWeek: Boolean,
    var days: String?,
    var habitEnds: String?,
    var habitEndsAtDate: String?,
    var habitEndsAfter: Int? = 0
) {
}