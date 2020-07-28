package com.example.goaltracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goaltracker.Model.Evidence
import kotlinx.android.synthetic.main.evidence_item.view.*

class EvidenceAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var evidenceList: List<Evidence> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EvidenceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.evidence_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is EvidenceViewHolder->{
                holder.bind(evidenceList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return evidenceList.size
    }

    fun sumbitList(list: List<Evidence>){
        evidenceList = list
    }

    class EvidenceViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.evidence_author_text_view
        val tagged_user: TextView = itemView.evidence_tagged_user_text_view
        val context: TextView = itemView.evidence_context_text_view
        val time: TextView = itemView.evidence_time_text_view
        val image: ImageView = itemView.evidence_image_view

        fun bind(evidence: Evidence){
            author.setText(evidence.author)
            tagged_user.setText(evidence.taggedPerson)
            context.setText(evidence.context)
            time.setText(evidence.timeStamp)

            Glide
                .with(context)
                .load(evidence.image)
                .centerCrop()
                .into(image)
        }
    }

}
