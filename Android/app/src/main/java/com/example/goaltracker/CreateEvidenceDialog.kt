package com.example.goaltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment

class CreateEvidenceDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.create_evidence_dialog,container,false)

        val saveEvidenceButton: Button = rootView.findViewById(R.id.save_create_evidence_button)
//        saveEvidenceButton.setOnClickListener{
//
//        }

        val cancelEvidenceButton: Button = rootView.findViewById(R.id.cancel_create_evidence_button)
//        cancelEvidenceButton.setOnClickListener{
//
//        }

//        val evidenceGoalSpinner: Spinner = rootView.findViewById(R.id.evidence_goal_spinner)
//        // create an arrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            requireContext(),
////            R.array.,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // apply the adapter to the the spinner
//            evidenceGoalSpinner.adapter = adapter
//        }


        return rootView
    }
}