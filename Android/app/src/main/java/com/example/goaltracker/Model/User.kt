package com.example.goaltracker.Model

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class User(
    var name: String,
    var id: String,
    @Embedded
    var goalsList: ArrayList<Goal> = ArrayList(),
    @Embedded
    var evidenceList: ArrayList<Evidence> = ArrayList()
){


}