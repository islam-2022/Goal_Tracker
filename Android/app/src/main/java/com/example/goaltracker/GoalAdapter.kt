package com.example.goaltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.manager.Lifecycle
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.ViewModel.GoalViewModel
import com.google.android.gms.common.api.internal.LifecycleActivity
import kotlinx.android.synthetic.main.goal_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GoalAdapter(requireActivity: FragmentActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var goalsList = ArrayList<Goal>()
    private val requireActivity: FragmentActivity

    init {
        this.requireActivity = requireActivity
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GoalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.goal_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GoalViewHolder->{
                holder.bind(goalsList.get(position),requireActivity)
            }
        }
    }

    override fun getItemCount(): Int {
        return goalsList.size
    }

    fun sumbitList(list: List<Goal>){
        goalsList = list as ArrayList<Goal>
    }


    class GoalViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val goalTitle: TextView = itemView.goal_name_text_view
        private val goalProgress: TextView = itemView.goal_progress_text_view
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.habit_recycle_view)
        private lateinit var habitAdapter: HabitAdapter

        fun bind(goal: Goal, requireActivity: FragmentActivity){
            goalTitle.text = goal.title
            goalProgress.setText(goal.progress.toString() + "%")
            recyclerView.apply{
                habitAdapter = HabitAdapter()
                val viewModel = ViewModelProvider(requireActivity).get(GoalViewModel::class.java)
                viewModel.getHabitsOfGoal(goal.id).observe(requireActivity,
                    object: Observer<List<Habit>> {
                        override fun onChanged(habits: List<Habit>?) {
                            habitAdapter.sumbitList(habits as ArrayList)
                            habitAdapter.notifyDataSetChanged()
                        }
                    })
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = habitAdapter
            }
        }
    }


}

