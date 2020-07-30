package com.example.goaltracker.Service

import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.Priority

class GoalService {

    fun getMockGoalData() : ArrayList<Goal> {
        val habit1 = Habit("got to Gym","",8, Priority.NORMAL,null)
        val habit2 = Habit("Eat Healthy","",7, Priority.HIGH,null)
        val habits1 = ArrayList<Habit>()
        habits1.add(habit1)
        habits1.add(habit2)
        val habit3 = Habit("Track expenses","",15, Priority.LOW,null)
        val habits2 = ArrayList<Habit>()
        habits2.add(habit3)
        val list = ArrayList<Goal>()
        list.add(
            Goal(
                "Health",
                10,
                habits1,
                ""
            )
        )
        list.add(
            Goal(
                "Finance",
                15,
                habits2,
                ""
            )
        )
        return list
    }
}