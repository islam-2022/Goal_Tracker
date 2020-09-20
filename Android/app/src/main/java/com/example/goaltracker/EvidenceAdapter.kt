package com.example.goaltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goaltracker.Model.Evidence
import com.example.goaltracker.ViewModel.EvidenceViewModel
import com.example.goaltracker.ViewModel.GoalViewModel
import kotlinx.android.synthetic.main.evidence_item.view.*
import kotlinx.coroutines.*

class EvidenceAdapter(requireActivity: FragmentActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var evidenceList = ArrayList<Evidence>()
    private val requireActivity: FragmentActivity

    init {
        this.requireActivity = requireActivity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EvidenceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.evidence_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is EvidenceViewHolder->{
                holder.bind(evidenceList[position],requireActivity)
            }
        }
    }

    override fun getItemCount(): Int {
        return evidenceList.size
    }

    fun sumbitList(list: ArrayList<Evidence>){
        evidenceList = list
    }

    fun addToList(evidence: Evidence){
        evidenceList.add(evidence)
    }

    class EvidenceViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.evidence_author_text_view
        val habitTitle: TextView = itemView.evidence_habit_title_text_view
        val context: TextView = itemView.evidence_context_text_view
        val time: TextView = itemView.evidence_time_text_view
        val image: ImageView = itemView.evidence_image_view

        fun bind(evidence: Evidence, requireActivity: FragmentActivity){
            GlobalScope.launch {
                author.setText(evidence.author)
                val viewModel =
                    ViewModelProvider(requireActivity).get(EvidenceViewModel::class.java)
                habitTitle.setText(viewModel.getHabit(evidence.habitId!!).title.toString())
                context.setText(evidence.context)
                time.setText(evidence.timeStamp)
                launch(Dispatchers.Main) {
                    Glide
                        .with(context)
                        .load(evidence.image)
                        .centerCrop()
                        .into(image)
                }
            }

        }
    }

}
