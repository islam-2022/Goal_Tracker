package com.example.goaltracker.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.goaltracker.Model.Evidence
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.Service.Database.MainDatabase
import com.example.goaltracker.Service.Repository.EvidenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EvidenceViewModel(application: Application): AndroidViewModel(application) {
    private val evidenceList: LiveData<List<Evidence>>
    private val habitList: LiveData<List<Habit>>
    private val repository: EvidenceRepository

    // constructor to initialize the list from the backend
    init {
        Log.i("EvidenceViewModel", "EvidenceViewModel created!")
        val evidenceDao = MainDatabase.getDatabaseInstance(application,viewModelScope).evidenceDao()
        repository = EvidenceRepository(evidenceDao)
        evidenceList = repository.evidenceList
        habitList = repository.habitList
    }

    // insert a  new evidence.
    fun insert(evidence: Evidence) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(evidence)
    }

    // update an existed evidence.
    fun update(evidence: Evidence) = viewModelScope.launch(Dispatchers.IO){
        repository.update(evidence)
    }

    // remove an evidence.
    fun delete(evidence: Evidence) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(evidence)
    }

    fun getAllEvidence(): LiveData<List<Evidence>> {
        return evidenceList
    }

    fun getHabitsList(): LiveData<List<Habit>>{
        return habitList
    }

    suspend fun getHabit(habitId: Int) :Habit{
        return repository.getHabit(habitId)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("EvidenceViewModel", "EvidenceViewModel destroyed!")
    }

}