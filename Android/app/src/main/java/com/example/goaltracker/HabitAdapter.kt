package com.example.goaltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracker.Model.Habit

class HabitAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var habitList = ArrayList<Habit>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HabitViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.habit_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HabitViewHolder->{
                holder.bind(habitList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    fun sumbitList(list: List<Habit>){
        habitList = list as ArrayList<Habit>
    }
//    fun insertHabit(habit: Habit){
//        habitList.add(habit)
//    }


    class HabitViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        private val habitTitle: TextView = itemView.findViewById(R.id.habit_title_textView)
        private val habitProgress: TextView = itemView.findViewById(R.id.habit_progress_text_view)

        fun bind(habit: Habit){
            habitTitle.setText(habit.title)
            habitProgress.setText(habit.progress.toString()+"%")
        }
    }
}
