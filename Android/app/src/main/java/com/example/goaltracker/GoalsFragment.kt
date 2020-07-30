package com.example.goaltracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracker.Extension.getHabit
import com.example.goaltracker.Extension.getHabitTimePeriod
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.Service.GoalService
import com.example.goaltracker.ViewModel.GoalViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


// todo add edit and delete features
class GoalsFragment: Fragment(R.layout.fragment_goals) {

    private lateinit var goalAdapter: GoalAdapter
    private lateinit var goalViewModel: GoalViewModel
    private val GREATE_HABIT_DIALOG: Int = 0

    override fun onCreateView(inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val rootView:View = inflater!!.inflate(R.layout.fragment_goals,container,false)

        val goalsRecycleView : RecyclerView = rootView.findViewById(R.id.goals_recycle_view)
        goalsRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            goalAdapter = GoalAdapter()
            adapter = goalAdapter
        }


        // create fake data.
        val goalData = GoalService().getMockGoalData()


        goalViewModel = ViewModelProviders.of(this).get(GoalViewModel::class.java)
//        goalViewModel.getAllGoals()
//            .observe(this, object: Observer<List<Goal>> {
//                override fun onChanged(goals: List<Goal>) {
//                    goalAdapter.sumbitList(goals)
//                }
//             })
        goalAdapter.sumbitList(goalData)


        val addGoalButton = rootView.findViewById<FloatingActionButton>(R.id.add_goal_button);
        addGoalButton.setOnClickListener{
            val dialog = CreateHabitDialog()
            dialog.setTargetFragment(this, GREATE_HABIT_DIALOG);
            dialog.show(requireFragmentManager(),"createGoal")
        }


        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GREATE_HABIT_DIALOG){
            if(resultCode == Activity.RESULT_OK){
                val bundle: Bundle? = data!!.extras
                val habitTimePeriod = bundle?.getHabitTimePeriod()
                var habit = bundle?.getHabit(habitTimePeriod)
                // todo view model. goal .add habit.

            }
        }
    }
}
