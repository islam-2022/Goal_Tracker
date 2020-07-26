package com.example.goaltracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.google.android.material.floatingactionbutton.FloatingActionButton


class GoalsFragment: Fragment(R.layout.fragment_goals) {

    private lateinit var goalAdapter: GoalAdapter
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
        val habit1 = Habit("got to Gym","",8,Priority.NORMAL,null)
        val habit2 = Habit("Eat Healthy","",7,Priority.HIGH,null)
        val habits1 = ArrayList<Habit>()
        habits1.add(habit1)
        habits1.add(habit2)
        val habit3 = Habit("Track expenses","",15,Priority.LOW,null)
        val habits2 = ArrayList<Habit>()
        habits2.add(habit3)
        val list = ArrayList<Goal>()
        list.add(
            Goal(
                "Health",
                10,
                habits1,
                ""
            )
        )
        list.add(
            Goal(
                "Finance",
                15,
                habits2,
                ""
            )
        )


        goalAdapter.sumbitList(list)

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
                var habitTimePeriod = HabitTimePeriod(
                    bundle!!.getInt("habitFrequencyNumber"),
                    bundle!!.getBoolean("isFrequencyPerWeek"),
                    bundle!!.get("daysSelected") as ArrayList<Days>,
                    bundle!!.getString("selectedRadioButton"),
                    bundle!!.getString("habitEndingAt"),
                    bundle!!.getInt("habitEndingAfter")
                )
                var habit = Habit(
                    bundle!!.getString("habitName"),
                    bundle!!.getString("habitDescription"),
                    0,
                    bundle!!.get("priority"),
                    habitTimePeriod
                )
                // todo view model. goal .add habit.

            }
        }
    }
}
