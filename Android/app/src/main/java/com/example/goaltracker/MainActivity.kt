package com.example.goaltracker

import EviFrag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavview.GoalsFrag
import com.example.bottomnavview.SettingsFrag
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val goalsFrag=GoalsFrag()
		val settingsFrag=SettingsFrag()
		val eviFrag=EviFrag()

		setCurrentFragment(goalsFrag)

		bottomNavigationView.setOnNavigationItemSelectedListener {
			when(it.itemId){
				R.id.goals->setCurrentFragment(goalsFrag)
				R.id.evidence->setCurrentFragment(eviFrag)
				R.id.settings->setCurrentFragment(settingsFrag)
			}
			true
		}

	}

	private fun setCurrentFragment(fragment: Fragment){
		supportFragmentManager.beginTransaction().apply {
			replace(R.id.flFragment,fragment)
			commit()
		}
	}

}
