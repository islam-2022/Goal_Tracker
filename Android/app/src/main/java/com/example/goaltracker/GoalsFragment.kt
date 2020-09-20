package com.example.goaltracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracker.Extension.getHabit
import com.example.goaltracker.Extension.getHabitTimePeriod
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.Model.HabitTimePeriod
import com.example.goaltracker.ViewModel.GoalViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope


class GoalsFragment: Fragment(R.layout.fragment_goals) {

    private lateinit var goalAdapter: GoalAdapter
    private lateinit var habitAdapter: HabitAdapter
    private lateinit var goalViewModel: GoalViewModel
    private val GREATE_HABIT_DIALOG: Int = 0


    override fun onCreateView(inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val rootView:View = inflater.inflate(R.layout.fragment_goals,container,false)

        val goalsRecycleView : RecyclerView = rootView.findViewById(R.id.goals_recycle_view)
        goalsRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            goalAdapter = GoalAdapter(requireActivity())
            adapter = goalAdapter
        }





        goalViewModel = ViewModelProvider(requireActivity()).get(GoalViewModel::class.java)
        goalViewModel.getAllGoals()
            .observe(viewLifecycleOwner, object : Observer<List<Goal>> {
                override fun onChanged(goals: List<Goal>) {
                    goalAdapter.sumbitList(goals)
                    goalAdapter.notifyDataSetChanged()
                }
             })



//        val goalData = GoalService().getMockGoalData()

//        goalViewModel = ViewModelProvider(this).get(GoalViewModel::class.java)
//        goalViewModel.getAllGoals()
//            .observe(this, object: Observer<List<Goal>> {
//                override fun onChanged(goals: List<Goal>) {
//                    goalAdapter.sumbitList(goals)
//                }
//             })
//        goalAdapter.sumbitList(goalData)


        val addGoalButton = rootView.findViewById<FloatingActionButton>(R.id.add_goal_button);
        addGoalButton.setOnClickListener{
            val dialog = CreateHabitDialog()
            dialog.setTargetFragment(this, GREATE_HABIT_DIALOG);
            dialog.show(parentFragmentManager,"createGoal")
        }



        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GREATE_HABIT_DIALOG){
            if(resultCode == Activity.RESULT_OK){
                val bundle: Bundle? = data!!.extras


                val habitTimePeriod = HabitTimePeriod(
                    bundle!!.getInt("habitFrequencyNumber"),
                    bundle.getBoolean("isFrequencyPerWeek"),
                    bundle.getString("daysSelected"),
                    bundle.getString("selectedRadioButton"),
                    bundle.getString("habitEndingAt"),
                    bundle.getInt("habitEndingAfter")
                )

                val goalOfTheHabit = bundle.getString("goalOfTheHabit")
                var goalId = 0
                goalViewModel.goals.forEachIndexed { index, item ->
                    if (item.toString() == goalOfTheHabit) {
                        goalId= index
                    }
                }

                var habit =  Habit(
                    bundle.getString("habitName"),
                    bundle.getString("habitDescription"),
                    0,
                    bundle.getString("priority"),
                    goalId,
                    0,
                    habitTimePeriod
                )

                goalViewModel.insert(habit, goalOfTheHabit!!)


            }
        }
    }
}
