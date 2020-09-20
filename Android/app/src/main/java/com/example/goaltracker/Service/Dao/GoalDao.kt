package com.example.goaltracker.Service.Dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit

@Dao
abstract class GoalDao{

    @Query("SELECT * FROM goal_table")
    abstract fun getAllGoals(): LiveData<List<Goal>>

    @Query("SELECT * FROM habit_table")
    abstract fun getAllHabits(): LiveData<List<Habit>>

    @Query("SELECT * FROM habit_table WHERE goalId=:goalId")
    abstract fun getHabitsOfGoal(goalId: Int): LiveData<List<Habit>>

    @Query("DELETE FROM habit_table WHERE id=:habitId")
    abstract fun deleteHabit(habitId: Int)

    @Query("DELETE FROM goal_table WHERE id=:goalId")
    abstract fun deleteGoal(goalId: Int)


    @Insert
    abstract suspend fun insert(habit: Habit)


    @Insert
    abstract suspend fun insert(goal: Goal)


}