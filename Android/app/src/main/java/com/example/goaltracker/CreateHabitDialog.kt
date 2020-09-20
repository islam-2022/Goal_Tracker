package com.example.goaltracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.DialogFragment

class CreateHabitDialog : DialogFragment() {

    private val SET_HABIT_TIME_PERIOD_DIALOG: Int = 1

    private var SET_TIME_PERIOD_FLAG = false
    private lateinit var habitName: EditText
    private lateinit var habitDescription: EditText
    private lateinit var setTimePeriodErrorMessage: TextView
    private var habitTimePeriodBundle: Bundle =  Bundle.EMPTY
    private lateinit var mentorMail: EditText

    override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val rootView:View = inflater.inflate(R.layout.create_habit_dialog,container,false)

        habitName = rootView.findViewById(R.id.habit_name_edit_text)
        habitDescription  = rootView.findViewById(R.id.habit_description_edit_text)
        setTimePeriodErrorMessage = rootView.findViewById(R.id.set_time_period_error_text_view)
        mentorMail = rootView.findViewById(R.id.mentor_mail_edit_text)
        val goalsSpinner: Spinner = rootView.findViewById(R.id.goals_spinner)
        val prioritySpinner: Spinner = rootView.findViewById(R.id.priority_spinner)

        val saveHabitButton = rootView.findViewById<Button>(R.id.save_create_habit_button)
        saveHabitButton.setOnClickListener{
            if(checkValidInputs()){
                val intent = Intent()
                intent.putExtra("habitName",habitName.text.toString())
                intent.putExtra("habitDescription",habitDescription.text.toString())
                intent.putExtra("goalOfTheHabit",goalsSpinner.selectedItem.toString())
                intent.putExtra("priority",prioritySpinner.selectedItem.toString())
                intent.putExtra("mentorMail",mentorMail.text.toString())
                intent.putExtras(habitTimePeriodBundle)
                targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK,intent)
                dismiss()
            }
        }


        val cancelHabitButton = rootView.findViewById<Button>(R.id.cancel_create_habit_button)
        cancelHabitButton.setOnClickListener{
            dismiss()
        }

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

        val setHabitTimePeriod = rootView.findViewById<Button>(R.id.set_habit_time_period_button)
        setHabitTimePeriod.setOnClickListener{
            val dialog = SelectHabitTime()
            dialog.arguments = this.habitTimePeriodBundle
            dialog.setTargetFragment(this, SET_HABIT_TIME_PERIOD_DIALOG);
            dialog.show(parentFragmentManager,"select time period")
        }

        return rootView
    }

    private fun checkValidInputs(): Boolean {
        var flag = true;
        if (habitName.text.toString().isEmpty()) {
            habitName.error = "Enter valid input"
            flag = false
        }
//        if (habitDescription.text.toString().isEmpty()) {
//            habitDescription.error = "Enter valid input"
//            flag = false
//        }
        if (!SET_TIME_PERIOD_FLAG) {
            setTimePeriodErrorMessage.visibility = View.VISIBLE
            flag = false
        }
        if(mentorMail.text.toString().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mentorMail.text.toString()).matches()){
            mentorMail.error = "Enter valid input"
            flag = false
        }
        return flag
    }


    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SET_HABIT_TIME_PERIOD_DIALOG){
            if(resultCode == Activity.RESULT_OK){
                habitTimePeriodBundle = data!!.extras!!
                SET_TIME_PERIOD_FLAG = true
            }
        }
    }
}

