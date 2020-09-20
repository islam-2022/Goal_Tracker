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

    private lateinit var habitFrequencyRepeatEverySpinner: Spinner
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
    private lateinit var habitEndingAfterSpinner: Spinner
    private lateinit var dateEditText: EditText

    private var selectedHabitTimeFrequency: String = "day"

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.select_habit_time_dialog,container,false)

        // initializing spinners views
        habitFrequencySpinner = rootView.findViewById(R.id.habit_time_frequency_spinner)
        habitFrequencyRepeatEverySpinner = rootView.findViewById(R.id.habit_time_frequency_repeat_every_spinner)
        habitEndingAfterSpinner = rootView.findViewById(R.id.habit_time_ending_after_spinner)
        habitEndingAfterSpinner.isEnabled = false
        dateEditText = rootView.findViewById(R.id.date_edit_text)
        // initializing check boxes
        sundayCheckBox = rootView.findViewById(R.id.sunday_check_box)
        mondayCheckBox = rootView.findViewById(R.id.monday_check_box)
        tuesdayCheckBox = rootView.findViewById(R.id.tuesday_check_box)
        wednesdayCheckBox  = rootView.findViewById(R.id.wednesday_check_box)
        thursdayCheckBox = rootView.findViewById(R.id.thursday_check_box)
        fridayCheckBox = rootView.findViewById(R.id.friday_check_box)
        saturdayCheckBox = rootView.findViewById(R.id.saturday_check_box)
        // initializing default selected radio button and the radio group
        selectedRadioButton = rootView.findViewById(R.id.never_radio_button)
        habitTimeEndingRadioGroup = rootView.findViewById(R.id.habit_time_ending_radio_group)

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
                if(selectedHabitTimeFrequency == "day" || selectedHabitTimeFrequency == "month" || selectedHabitTimeFrequency == "year")
                    daysVisibility(false)
                else if(selectedHabitTimeFrequency.equals("week"))
                    daysVisibility(true)
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) { }

        }

        val numbersArray = ArrayList<Int>()
        for (i in 1..100) {
            numbersArray.add(i)
        }
        val habitFrequencyRepeatSpinnerAdapter = ArrayAdapter<Int>(context!!,android.R.layout.simple_spinner_item,numbersArray)
        habitFrequencyRepeatSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        habitFrequencyRepeatEverySpinner.adapter = habitFrequencyRepeatSpinnerAdapter

        val habitEndingAfterSpinnerAdapter = ArrayAdapter<Int>(context!!,android.R.layout.simple_spinner_item,numbersArray)
        habitEndingAfterSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        habitEndingAfterSpinner.adapter = habitEndingAfterSpinnerAdapter


        // set up habit ending time radio group listener
        habitTimeEndingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            var radioId: Int =  habitTimeEndingRadioGroup.checkedRadioButtonId
            val habitEndingAfterTextView: TextView = rootView.findViewById(R.id.habit_time_ending_after_text_view)
            selectedRadioButton = rootView.findViewById(radioId)
            if(selectedRadioButton.text == "Never"){
                dateEditText.isEnabled = false
                habitEndingAfterSpinner.isEnabled = false
                habitEndingAfterTextView.isEnabled = false
            }
            else if (selectedRadioButton.text == "On") {
                dateEditText.isEnabled = true
                habitEndingAfterSpinner.isEnabled = false
                habitEndingAfterTextView.isEnabled = false
            }
            else if(selectedRadioButton.text == "After"){
                dateEditText.isEnabled = false
                habitEndingAfterSpinner.isEnabled = true
                habitEndingAfterTextView.isEnabled = true
            }



        }

        // set up finish button listener
        val finishButton: Button = rootView.findViewById(R.id.finish_select_time_button)
        finishButton.setOnClickListener{
            if(checkValidInputs()){
                val intent = Intent()
                println(habitEndingAfterSpinner.selectedItem.toString().toInt())
                intent.putExtra("habitFrequencyNumber",habitFrequencyRepeatEverySpinner.selectedItem.toString().toInt())
                intent.putExtra("FrequencyPerItemPosition",habitFrequencySpinner.selectedItemPosition)
                intent.putExtra("isFrequencyPerWeek",selectedHabitTimeFrequency == "week")
                if(selectedHabitTimeFrequency == "week") {
                    var daysSelected = String()
                    if(sundayCheckBox.isChecked)
                        daysSelected.plus(Days.Sunday.toString()+",")
                    if(mondayCheckBox.isChecked)
                        daysSelected.plus(Days.Monday.toString()+",")
                    if(tuesdayCheckBox.isChecked)
                        daysSelected.plus(Days.Tuesday.toString()+",")
                    if(wednesdayCheckBox.isChecked)
                        daysSelected.plus(Days.Wednesday.toString()+",")
                    if(thursdayCheckBox.isChecked)
                        daysSelected.plus(Days.Thursday.toString()+",")
                    if(fridayCheckBox.isChecked)
                        daysSelected.plus(Days.Friday.toString()+",")
                    if(saturdayCheckBox.isChecked)
                        daysSelected.plus(Days.Saturday.toString()+",")
                    daysSelected.dropLast(1)
                    intent.putExtra("daysSelected",daysSelected)
                }
                intent.putExtra("selectedRadioButton",selectedRadioButton.text.toString())
                if(selectedRadioButton.text.toString() == "On")
                    intent.putExtra("habitEndingAt",dateEditText.text.toString())
                else if(selectedRadioButton.text.toString() == "After")
                    intent.putExtra("habitEndingAfter",habitEndingAfterSpinner.selectedItem.toString())
                targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK,intent)
                dismiss()
            }
        }

        // load a saved state if exists
        var bundle: Bundle = arguments!!
        if (bundle != Bundle.EMPTY){
            loadData(bundle)
        }

        return rootView
    }

    // change the visibility of the days linear layout.
    // b true -> visible   --   b false -> gone
    private fun daysVisibility(b: Boolean) {
        println(b)
        val daysOfWeekTextLinearLayout: LinearLayout = rootView.findViewById(R.id.days_of_week_text_linear_layout)
        val daysOfWeekCheckBoxLinearLayout: LinearLayout = rootView.findViewById(R.id.days_of_week_check_box_linear_layout)
        val repeatOnTextView: TextView = rootView.findViewById(R.id.repeat_on_days_text_view)
        if(b){
            daysOfWeekTextLinearLayout.visibility = View.VISIBLE
            daysOfWeekCheckBoxLinearLayout.visibility = View.VISIBLE
            repeatOnTextView.visibility = View.VISIBLE
        }else{
            daysOfWeekTextLinearLayout.visibility = View.GONE
            daysOfWeekCheckBoxLinearLayout.visibility = View.GONE
            repeatOnTextView.visibility = View.GONE   
        }
    }

    // this function load the saved state of the time period dialog.
    private fun loadData(bundle: Bundle) {
        habitFrequencyRepeatEverySpinner.setSelection(bundle.getInt("habitFrequencyNumber")-1)
        habitFrequencySpinner.setSelection(bundle.getInt("FrequencyPerItemPosition"))
        if(bundle.getBoolean("isFrequencyPerWeek")){
            daysVisibility(true)
            val days : Array<String> = bundle.getString("daysSelected")!!.split(",").toTypedArray()
            for(day in days){
                when(day) {
                    Days.Sunday.toString() -> sundayCheckBox.isChecked = true
                    Days.Monday.toString() -> mondayCheckBox.isChecked = true
                    Days.Tuesday.toString() -> tuesdayCheckBox.isChecked = true
                    Days.Wednesday.toString() -> wednesdayCheckBox.isChecked = true
                    Days.Thursday.toString() -> thursdayCheckBox.isChecked = true
                    Days.Friday.toString() -> fridayCheckBox.isChecked = true
                    Days.Saturday.toString() -> saturdayCheckBox.isChecked = true
                }
            }
        }
        println(bundle.getString("selectedRadioButton").toString())
        if(bundle.getString("selectedRadioButton").toString() == "On") {
            rootView.findViewById<RadioButton>(R.id.on_radio_button).isChecked = true
            dateEditText.setText(bundle.getString("habitEndingAt"))
        }
        else if(bundle.getString("selectedRadioButton").toString() == "After") {
            rootView.findViewById<RadioButton>(R.id.after_radio_button).isChecked = true
            habitEndingAfterSpinner.setSelection(bundle.getString("habitEndingAfter")!!.toInt()-1)
        }
    }

    // validation func
    private fun checkValidInputs(): Boolean {
        var flag = true
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
        return flag
    }


    // handle the dialog size.
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }


    // validate the date provided
    @Throws(ParseException::class)
    fun validDate(dateStr: String) {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = formatter.parse(dateStr)
        println(date)
    }



}
