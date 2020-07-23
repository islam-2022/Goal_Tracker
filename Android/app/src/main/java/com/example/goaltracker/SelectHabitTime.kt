package com.example.goaltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class SelectHabitTime: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.select_habit_time_dialog,container,false)






        return rootView
    }
}