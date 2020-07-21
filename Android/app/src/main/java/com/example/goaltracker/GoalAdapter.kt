package com.example.goaltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracker.Model.Goal
import kotlinx.android.synthetic.main.goal_item.view.*

class GoalAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var goalsList: List<Goal> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GoalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.goal_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GoalViewHolder->{
                holder.bind(goalsList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return goalsList.size
    }

    fun sumbitList(list: List<Goal>){
        goalsList = list
    }

    class GoalViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview) {
        val goalTitle: TextView = itemview.goal_title_textView
        val goalProgress: TextView = itemview.progress_text_view

        fun bind(goal: Goal){
            goalTitle.setText(goal.title)
            goalProgress.setText(goal.progress.toString()+"%")
        }

    }

}
