package com.example.goaltracker.ViewModel

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.goaltracker.Extension.getHabit
import com.example.goaltracker.Extension.getHabitTimePeriod
import com.example.goaltracker.Goals
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.R
import com.example.goaltracker.Service.Database.MainDatabase
import com.example.goaltracker.Service.Repository.GoalRepository
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class GoalViewModel(application: Application): AndroidViewModel(application){
    // defining a viewModelJob which allows yo cancel all coroutines started by this viewModel when
    // the viewModel is no longer used and destroyed.
    private var viewModelJob = Job()

    // defining a uiScope for the coroutines. The scope determines what thread the coroutine will run on.
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val goalList: LiveData<List<Goal>>
    private val repository: GoalRepository
    val goals: Array<Goals>

    // constructor to initialize the list from the backend
    init {
        Log.i("GoalViewModel", "GoalViewModel created!")
        val goalDao = MainDatabase.getDatabaseInstance(application, viewModelScope).goalDao()
        repository = GoalRepository(goalDao)
        goals = enumValues<Goals>()
        goalList = repository.getGoalList()
    }

    fun insert(habit: Habit, goalTitle: String){
        var goalExists = false
        goalList.value?.forEach{
            if(it.title == goalTitle)
                goalExists = true
        }
        uiScope.launch {
            insert(habit)
            if(!goalExists)
                insert(Goal(goalTitle, 0,habit.goalId))
        }
    }

    suspend fun insert(goal: Goal) {
        repository.insert(goal)
    }

    suspend fun insert(habit: Habit){
        val goalId = habit.goalId
        repository.insert(habit)
    }


    // insert or update or delete a habit in existence goal.
    fun update() {

    }

    // remove a goal when no habits in it.
    fun deleteHabit(habitId: Int) {
        uiScope.launch {
            repository.deleteHabit(habitId)
        }
    }

    fun deleteGoal(goalId: Int){
        uiScope.launch {
            repository.deleteGoal(goalId)
        }
    }


    fun getAllGoals(): LiveData<List<Goal>> {
        return goalList
    }


    fun getHabitsOfGoal(id: Int): LiveData<List<Habit>> {
         return repository.getHabitsOfGoal(id)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        uiScope.coroutineContext.cancelChildren()
        Log.i("GoalViewModel", "GoalViewModel destroyed!")
    }

}