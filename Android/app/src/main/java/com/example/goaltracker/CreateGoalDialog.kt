package com.example.goaltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class CreateGoalDialog : DialogFragment() {

    override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val viewRoot:View = inflater.inflate(R.layout.add_goal_dialog,container,false)

        val saveGoalButton = viewRoot.findViewById<Button>(R.id.save_goal_button)
//        saveGoalButton.setOnClickListener{
//
//        }


        val cancelGoalButton = viewRoot.findViewById<Button>(R.id.cancel_goal_button)
//        cancelGoalButton.setOnClickListener{
//
//        }



        return viewRoot
    }

}