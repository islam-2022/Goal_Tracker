package com.example.goaltracker.Service.Repository

import androidx.lifecycle.LiveData
import com.example.goaltracker.Model.Evidence
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.Service.Dao.EvidenceDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class EvidenceRepository(private val evidenceDao: EvidenceDao) {
    val evidenceList : LiveData<List<Evidence>> = evidenceDao.getAllEvidence()
    val habitList: LiveData<List<Habit>> = evidenceDao.getAllHabits()

    suspend fun insert(evidence: Evidence){
        evidenceDao.insert(evidence)
    }

    suspend fun update(evidence: Evidence){
        evidenceDao.update(evidence)
    }

    suspend fun delete(evidence: Evidence){
        evidenceDao.delete(evidence)
    }

    suspend fun getHabit(habitId: Int) :Habit{
        return evidenceDao.getHabit(habitId)
    }


}