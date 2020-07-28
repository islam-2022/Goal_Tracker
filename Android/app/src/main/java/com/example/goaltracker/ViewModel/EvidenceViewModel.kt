package com.example.goaltracker.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.goaltracker.Model.Evidence

class EvidenceViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var evidenceList: LiveData<List<Evidence>>

    // constructor to initialize the list from the backend
    init {
        Log.i("EvidenceViewModel", "EvidenceViewModel created!")
    }

    // insert a  new evidence.
    fun insert(){

    }

    // update an existed evidence.
    fun update(){

    }

    // remove an evidence.
    fun delete(){

    }

    fun getAllEvidence(): LiveData<List<Evidence>> {
        return evidenceList
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("EvidenceViewModel", "EvidenceViewModel destroyed!")
    }

}