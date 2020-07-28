package com.example.goaltracker.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.goaltracker.Model.Goal

class GoalViewModel(application: Application): AndroidViewModel(application){
    private lateinit var goalList: LiveData<List<Goal>>

    // constructor to initialize the list from the backend
    init {
        Log.i("GoalViewModel", "GoalViewModel created!")
    }


    // insert a  new goal associate with some habits.
    fun insert() {

    }

    // insert or update or delete a habit in existence goal.
    fun update() {

    }

    // remove a goal when no habits in it.
    fun delete() {

    }

    fun getAllGoals(): LiveData<List<Goal>> {
        return goalList
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GoalViewModel", "GoalViewModel destroyed!")
    }

}