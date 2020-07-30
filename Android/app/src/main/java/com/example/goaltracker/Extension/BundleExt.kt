package com.example.goaltracker.Extension

import android.os.Bundle
import com.example.goaltracker.Days
import com.example.goaltracker.HabitTimePeriod
import com.example.goaltracker.Model.Habit

fun Bundle.getHabitTimePeriod(): HabitTimePeriod {
    return HabitTimePeriod(
        this.getInt("habitFrequencyNumber"),
        this.getBoolean("isFrequencyPerWeek"),
        this.get("daysSelected") as ArrayList<Days>,
        this.getString("selectedRadioButton"),
        this.getString("habitEndingAt"),
        this.getInt("habitEndingAfter")
    )
}

fun Bundle.getHabit(habitTimePeriod: HabitTimePeriod?): Habit {
    return Habit(
        this.getString("habitName"),
        this.getString("habitDescription"),
        0,
        this.get("priority"),
        habitTimePeriod
    )
}