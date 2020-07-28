package com.example.goaltracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goaltracker.Model.Evidence
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.User
import com.example.goaltracker.ViewModel.EvidenceViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// todo add edit and delete features
class EvidenceFragment:Fragment(R.layout.fragment_evidence) {

	val BASE_URL="https://evidenceapinodejs.herokuapp.com/"
	private lateinit var evidenceAdapter: EvidenceAdapter
    private val CREATE_EVIDENCE_DIALOG: Int = 0
    private lateinit var evidenceViewModel: EvidenceViewModel

    override fun onCreateView(
                            inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
        val rootView:View = inflater.inflate(R.layout.fragment_evidence,container,false)

        val evidenceRecycleView : RecyclerView = rootView.findViewById(R.id.evidence_recycle_view)
        evidenceRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            evidenceRecycleView.setHasFixedSize(true)
            evidenceAdapter = EvidenceAdapter()
            evidenceRecycleView.adapter = evidenceAdapter
        }
		//KI CODE
		val list = ArrayList<Evidence>()
		list.add(Evidence("user1","user2","evidence1","5h","",false))
//		list.add(Evidence("user2","user1","evidence2","6h","",false))

		getCurrentData(object: Callback<List<Evidence>> {
			override fun onFailure(call: Call<List<Evidence>>, t: Throwable) {
				Log.d("+++here","+++onfailure")
			}
			override fun onResponse(call: Call<List<Evidence>>, response: Response<List<Evidence>>){
				val datas=response.body()

				if (datas != null) {
					for(data in datas){
//					Log.d("+++here "," ++data is "+datas.toString())
					list.add(Evidence(data.author,data.taggedPerson,data.context,data.timeStamp,"",false))
						Log.d("+++here "," ++list is "+list.toString())

						evidenceAdapter.sumbitList(list)
					}
				}
			}
		})
		//KI CODE end

        // create fake data.
//        var user1 = User("user1","1")
//        var user2 = User("user2","2")
//        val list = ArrayList<Evidence>()
//        list.add(Evidence(user1,user2,"evidence1","5h","",false))
//        list.add(Evidence(user2,user1,"evidence2","6h","",false))

        evidenceViewModel = ViewModelProviders.of(this).get(EvidenceViewModel::class.java)
//        evidenceViewModel.getAllEvidence().observe(this, object: Observer<List<Evidence>> {
//            override fun onChanged(evidenceList: List<Evidence>) {
//                evidenceAdapter.sumbitList(evidenceList)
//            }
//        })

        evidenceAdapter.sumbitList(list)

        val addEvidenceButton = rootView.findViewById<FloatingActionButton>(R.id.add_evidence_button);
        addEvidenceButton.setOnClickListener{
            val dialog = CreateEvidenceDialog()
            dialog.setTargetFragment(this, CREATE_EVIDENCE_DIALOG);
            dialog.show(requireFragmentManager(),"createEvidence")
        }

        return rootView
    }

	private fun getCurrentData(callback: Callback<List<Evidence>>) {
		val api = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		val apiRequest = api.create(ApiRequest::class.java)
		val call = apiRequest.getEvidence()
		call.enqueue(callback)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CREATE_EVIDENCE_DIALOG){
            if(resultCode == Activity.RESULT_OK){
                val bundle: Bundle? = data!!.extras
            }
        }
    }

}
