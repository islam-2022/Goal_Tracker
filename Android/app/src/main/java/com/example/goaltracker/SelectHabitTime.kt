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
import java.text.ParseException
import java.text.SimpleDateFormat


class SelectHabitTime: DialogFragment() {

    private lateinit var habitFrequencyEditText:EditText
    private lateinit var habitFrequencySpinner: Spinner
    private lateinit var sundayCheckBox: CheckBox
    private lateinit var mondayCheckBox: CheckBox
    private lateinit var tuesdayCheckBox: CheckBox
    private lateinit var wednesdayCheckBox: CheckBox
    private lateinit var thursdayCheckBox: CheckBox
    private lateinit var fridayCheckBox: CheckBox
    private lateinit var saturdayCheckBox: CheckBox
    private lateinit var habitTimeEndingRadioGroup: RadioGroup
    private lateinit var selectedRadioButton: RadioButton
    private lateinit var habitEndingAfterEditText: EditText
    private lateinit var dateEditText: EditText

    private var selectedHabitTimeFrequency: String = "day"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.select_habit_time_dialog,container,false)


        habitFrequencySpinner = rootView.findViewById(R.id.habit_time_frequency_spinner)
        // create an arrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.time_period,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // apply the adapter to the the spinner
            habitFrequencySpinner.adapter = adapter
        }

        // set listener for habit frequency spinner
        habitFrequencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                selectedHabitTimeFrequency = parentView!!.getItemAtPosition(position).toString()
                val daysOfWeekTextLinearLayout: LinearLayout = rootView.findViewById(R.id.days_of_week_text_linear_layout)
                val daysOfWeekCheckBoxLinearLayout: LinearLayout = rootView.findViewById(R.id.days_of_week_check_box_linear_layout)
                val repeatOnTextView: TextView = rootView.findViewById(R.id.repeat_on_days_text_view)
                if(selectedHabitTimeFrequency == "day" || selectedHabitTimeFrequency == "month" || selectedHabitTimeFrequency == "year"){
                    daysOfWeekTextLinearLayout.visibility = View.GONE
                    daysOfWeekCheckBoxLinearLayout.visibility = View.GONE
                    repeatOnTextView.visibility = View.GONE
                }
                else if(selectedHabitTimeFrequency.equals("week")){
                    daysOfWeekTextLinearLayout.visibility = View.VISIBLE
                    daysOfWeekCheckBoxLinearLayout.visibility = View.VISIBLE
                    repeatOnTextView.visibility = View.VISIBLE
                }
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) { }
        }


        habitFrequencyEditText = rootView.findViewById(R.id.habit_time_frequency_edit_text)
        sundayCheckBox = rootView.findViewById(R.id.sunday_check_box)
        mondayCheckBox = rootView.findViewById(R.id.monday_check_box)
        tuesdayCheckBox = rootView.findViewById(R.id.tuesday_check_box)
        wednesdayCheckBox  = rootView.findViewById(R.id.wednesday_check_box)
        thursdayCheckBox = rootView.findViewById(R.id.thursday_check_box)
        fridayCheckBox = rootView.findViewById(R.id.friday_check_box)
        saturdayCheckBox = rootView.findViewById(R.id.saturday_check_box)
        selectedRadioButton = rootView.findViewById(R.id.never_radio_button)

        habitEndingAfterEditText = rootView.findViewById(R.id.habit_time_ending_after_edit_text)
        dateEditText = rootView.findViewById(R.id.date_edit_text)
        // set up habit ending time radio group listener
        habitTimeEndingRadioGroup = rootView.findViewById(R.id.habit_time_ending_radio_group)
        habitTimeEndingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            var radioId: Int =  habitTimeEndingRadioGroup.checkedRadioButtonId
            val habitEndingAfterTextView: TextView = rootView.findViewById(R.id.habit_time_ending_after_text_view)
            selectedRadioButton = rootView.findViewById(radioId)
            if(selectedRadioButton.text == "Never"){
                dateEditText.isEnabled = false
                habitEndingAfterEditText.isEnabled = false
                habitEndingAfterTextView.isEnabled = false
            }
            else if (selectedRadioButton.text == "On") {
                dateEditText.isEnabled = true
                habitEndingAfterEditText.isEnabled = false
                habitEndingAfterTextView.isEnabled = false
            }
            else if(selectedRadioButton.text == "After"){
                dateEditText.isEnabled = false
                habitEndingAfterEditText.isEnabled = true
                habitEndingAfterTextView.isEnabled = true
            }
        }

        // set up finish button listener
        val finishButton: Button = rootView.findViewById(R.id.finish_select_time_button)
        finishButton.setOnClickListener{
            if(checkValidInputs()){
                val intent = Intent()
                intent.putExtra("habitFrequencyNumber",habitFrequencyEditText.text.toString())
                intent.putExtra("isFrequencyPerWeek",selectedHabitTimeFrequency=="week")
                if(selectedHabitTimeFrequency == "day") {
                    val daysSelected = ArrayList<Days>()
                    if(sundayCheckBox.isChecked)
                        daysSelected.add(Days.Sunday)
                    if(mondayCheckBox.isChecked)
                        daysSelected.add(Days.Monday)
                    if(wednesdayCheckBox.isChecked)
                        daysSelected.add(Days.Tuesday)
                    if(thursdayCheckBox.isChecked)
                        daysSelected.add(Days.Wednesday)
                    if(fridayCheckBox.isChecked)
                        daysSelected.add(Days.Thursday)
                    if(saturdayCheckBox.isChecked)
                        daysSelected.add(Days.Friday)
                    if(tuesdayCheckBox.isChecked)
                        daysSelected.add(Days.Saturday)
                    intent.putExtra("daysSelected",daysSelected)
                }
                intent.putExtra("selectedRadioButton",selectedRadioButton.text.toString())
                if(selectedRadioButton.text.toString() == "On")
                    intent.putExtra("habitEndingAt",dateEditText.text.toString())
                else if(selectedRadioButton.text.toString() == "After")
                    intent.putExtra("habitEndingAfter",habitEndingAfterEditText.text.toString())
                targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK,intent)
                dismiss()
            }
        }

        return rootView
    }

    private fun checkValidInputs(): Boolean {
        var flag = true
        val timeFrequency: String = habitFrequencyEditText.text.toString()
        if(timeFrequency.isEmpty()){
            habitFrequencyEditText.error = "Enter valid input"
            flag = false
        }
        val timePeriod: String = habitFrequencySpinner.selectedItem.toString()
        if(timePeriod == "week"){
            if(!sundayCheckBox.isChecked&&!mondayCheckBox.isChecked&&!tuesdayCheckBox.isChecked&&!wednesdayCheckBox.isChecked&&
                        !thursdayCheckBox.isChecked&&!fridayCheckBox.isChecked&&!saturdayCheckBox.isChecked) {
                Toast.makeText(context,"choose at least one day!",Toast.LENGTH_SHORT).show()
                flag = false
            }
        }
        if(selectedRadioButton.text == "On"){
            try {
                validDate(dateEditText.text.toString())
            }
            catch (e: ParseException){
                dateEditText.error = "Enter valid date"
                flag = false
            }
        }
        else if(selectedRadioButton.text == "After"){
            if(habitEndingAfterEditText.text.toString().isEmpty()){
                habitEndingAfterEditText.error = "Enter valid input"
                flag = false
            }
        }
        return flag
    }


    // handle the dialog size.
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }


    // validate the date provided
    @Throws(ParseException::class)
    fun validDate(dateStr: String) {
        var formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = formatter.parse(dateStr)
        println(date)
    }



}
