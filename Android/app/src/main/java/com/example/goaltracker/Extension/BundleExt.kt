package com.example.goaltracker.Extension

import android.os.Bundle
import com.example.goaltracker.Model.HabitTimePeriod
import com.example.goaltracker.Model.Habit

fun Bundle.getHabitTimePeriod(): HabitTimePeriod {
    return HabitTimePeriod(
        this.getString("habitFrequencyNumber")!!.toInt(),
        this.getBoolean("isFrequencyPerWeek"),
        this.getString("daysSelected"),
        this.getString("selectedRadioButton"),
        this.getString("habitEndingAt")
//        this.getString("habitEndingAfter")!!.toIntOrNull()
    )
}

fun Bundle.getHabit(habitTimePeriod: HabitTimePeriod?, goalId: Int): Habit {

    return Habit(
        this.getString("habitName"),
        this.getString("habitDescription"),
        0,
        this.getString("priority"),
        goalId,
        0,
        habitTimePeriod!!
    )

}

