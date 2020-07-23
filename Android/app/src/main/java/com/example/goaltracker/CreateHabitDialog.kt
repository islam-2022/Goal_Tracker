package com.example.goaltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment

class CreateHabitDialog : DialogFragment() {

    override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val rootView:View = inflater.inflate(R.layout.create_habit_dialog,container,false)

        val saveHabitButton = rootView.findViewById<Button>(R.id.save_create_habit_button)
//        saveHabitButton.setOnClickListener{
//
//        }


        val cancelHabitButton = rootView.findViewById<Button>(R.id.cancel_create_habit_button)
//        cancelHabitButton.setOnClickListener{
//
//        }

        val goalsSpinner: Spinner = rootView.findViewById(R.id.goals_spinner)
        // create an arrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.goals_array,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            // specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // apply the adapter to the the spinner
            goalsSpinner.adapter = adapter
        }




        val prioritySpinner: Spinner = rootView.findViewById(R.id.priority_spinner)
        // create an arrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.habits_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // apply the adapter to the the spinner
            prioritySpinner.adapter = adapter
        }


        return rootView
    }

}