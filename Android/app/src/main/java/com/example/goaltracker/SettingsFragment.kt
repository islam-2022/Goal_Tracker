package com.example.goaltracker
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment: Fragment(R.layout.fragment_settings), AdapterView.OnItemClickListener {
    var list = ArrayList<String>()
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val rootView: View = inflater!!.inflate(R.layout.fragment_settings,container,false)


        list.add("Account")
        list.add("Share")
        list.add("log out")
        list.add("About")
        mAuth= FirebaseAuth.getInstance()

        val adapter = context?.let { ArrayAdapter(it,R.layout.settings_item,list) }

        val listView = rootView.findViewById<ListView>(R.id.settings_list_view)
        listView.setAdapter(adapter)
        listView.setOnItemClickListener(this)


        return rootView

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val pos = list.get(position)
        when(pos)
        {
            "Account" -> Toast.makeText(context, "Account", Toast.LENGTH_SHORT).show()
            "Share" -> Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show()
            "log out" -> {mAuth!!.signOut()
                (this.activity as MainActivity?)?.toLogin()
            }
            "About" -> Toast.makeText(context, "About", Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show()
    }
}
