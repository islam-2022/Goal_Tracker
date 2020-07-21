package com.example.goaltracker
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class SettingsFragment: Fragment(R.layout.fragment_settings) {
    override fun onCreateView(
                                inflater: LayoutInflater,
                                container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {

        val rootView: View = inflater!!.inflate(R.layout.fragment_settings,container,false)

        var list = ArrayList<String>()
        list.add("Account")
        list.add("Share")
        list.add("log out")
        list.add("About")

        val adapter = context?.let { ArrayAdapter(it,R.layout.settings_item,list) }

        val listView = rootView.findViewById<ListView>(R.id.settings_list_view)
        listView.setAdapter(adapter)

        return rootView

    }
}
