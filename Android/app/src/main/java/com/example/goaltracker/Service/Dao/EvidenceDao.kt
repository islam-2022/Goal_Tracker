package com.example.goaltracker.Service.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.goaltracker.Model.Evidence
import com.example.goaltracker.Model.Habit

@Dao
abstract class EvidenceDao{

    @Insert
    abstract suspend fun insert(evidence: Evidence)

    @Update
    abstract suspend fun update(evidence: Evidence)

    @Delete
    abstract suspend fun delete(evidence: Evidence)

    @Query("SELECT * FROM evidence_table")
    abstract fun getAllEvidence(): LiveData<List<Evidence>>

    @Query("SELECT * FROM habit_table")
    abstract fun getAllHabits(): LiveData<List<Habit>>

    @Query("SELECT * FROM habit_table WHERE id=:habitId")
    abstract fun getHabit(habitId: Int): Habit

}