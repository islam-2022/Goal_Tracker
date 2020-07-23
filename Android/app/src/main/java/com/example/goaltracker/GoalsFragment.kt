package com.example.goaltracker

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
        val habit1 = Habit("got to Gym","",8,Priority.NORMAL,"")
        val habit2 = Habit("Eat Healthy","",7,Priority.HIGH,"")
        val habits1 = ArrayList<Habit>()
        habits1.add(habit1)
        habits1.add(habit2)
        val habit3 = Habit("Track expenses","",15,Priority.LOW,"")
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
            dialog.show(requireFragmentManager(),"createGoal")
        }


        return rootView
    }
}
