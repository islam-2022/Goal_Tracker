package com.example.goaltracker.Service.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.Service.Dao.GoalDao
import kotlinx.coroutines.*

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class GoalRepository(private val goalDao: GoalDao) {

    // defining a uiScope for the coroutines. The scope determines what thread the coroutine will run on.
    private val uiScope = CoroutineScope(Dispatchers.Main)



//    var goalList: MutableLiveData<List<Goal>> = goalDao.getAllGoals()
//    var habitList: MutableLiveData<List<Habit>> = goalDao.getAllHabits()


    suspend fun insert(goal: Goal) {
        goalDao.insert(goal)
    }

    suspend fun insert(habit: Habit){
        goalDao.insert(habit)
//        insertHabitAsyncTask(goalDao).execute(habit)
    }

    suspend fun deleteHabit(habitId: Int){
        goalDao.deleteHabit(habitId)
    }

    suspend fun deleteGoal(goalId: Int){
        goalDao.deleteGoal(goalId)
    }

    fun getGoalList(): LiveData<List<Goal>>{
        return goalDao.getAllGoals()
    }
    fun getHabitsOfGoal(goalId: Int): LiveData<List<Habit>>{
        return goalDao.getHabitsOfGoal(goalId)
    }


}