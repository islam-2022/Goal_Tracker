package com.example.goaltracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracker.Model.Goal
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
            goalsRecycleView.setHasFixedSize(true)
            goalAdapter = GoalAdapter()
            goalsRecycleView.adapter = goalAdapter
        }

        // create fake data.
        val list = ArrayList<Goal>()
        list.add(
            Goal(
                "Goal1",
                "goal1 description",
                10
            )
        )
        list.add(
            Goal(
                "Goal2",
                "goal2 description",
                20
            )
        )
        list.add(
            Goal(
                "Goal3",
                "goal1 description",
                30
            )
        )
        list.add(
            Goal(
                "Goal4",
                "goal2 description",
                40
            )
        )
        list.add(
            Goal(
                "Goal5",
                "goal1 description",
                50
            )
        )
        list.add(
            Goal(
                "Goal6",
                "goal2 description",
                60
            )
        )
        list.add(
            Goal(
                "Goal7",
                "goal1 description",
                70
            )
        )
        list.add(
            Goal(
                "Goal8",
                "goal2 description",
                80
            )
        )
        list.add(
            Goal(
                "Goal9",
                "goal1 description",
                90
            )
        )

        goalAdapter.sumbitList(list)

        val addGoalButton = rootView.findViewById<FloatingActionButton>(R.id.add_goal_button);
        addGoalButton.setOnClickListener{
            val dialog = CreateGoalDialog()
            dialog.show(requireFragmentManager(),"createGoal")
        }


        return rootView
    }
}
