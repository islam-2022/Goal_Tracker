package com.example.goaltracker

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	lateinit var toggle: ActionBarDrawerToggle
	private var mAuth: FirebaseAuth? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val goalsFragment=GoalsFragment()
		val settingsFragment=SettingsFragment()
		val evidenceFragment= EvidenceFragment()

		setCurrentFragment(goalsFragment)

		mAuth= FirebaseAuth.getInstance()
		bottomNavigationView.setOnNavigationItemSelectedListener {
			when(it.itemId){
				R.id.goals->setCurrentFragment(goalsFragment)
				R.id.evidence->setCurrentFragment(evidenceFragment)
				R.id.settings->setCurrentFragment(settingsFragment)
			}
			true
		}

		toggle=ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
		drawerLayout.addDrawerListener(toggle)
		toggle.syncState()

		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		navView.setNavigationItemSelectedListener {
			when(it.itemId){
				R.id.mItem1 -> Toast.makeText(applicationContext,"item1 clicked",
					Toast.LENGTH_SHORT).show()

				R.id.mItem2 -> Toast.makeText(applicationContext,"item2 clicked",
					Toast.LENGTH_SHORT).show()

				R.id.mItem3 -> {
					mAuth!!.signOut()
						(this.activity as MainActivity?)?.toLogin()
					this.toLogin()
				}
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

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if(toggle.onOptionsItemSelected(item)){
			return true
		}
		return super.onOptionsItemSelected(item)
	}
	fun toLogin()
	{
		val intent= Intent(this, LoginActivity::class.java)
		startActivity(intent)
	}

}
