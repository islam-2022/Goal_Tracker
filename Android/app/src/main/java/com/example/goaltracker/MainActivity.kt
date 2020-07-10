package com.example.goaltracker

import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        // NOT APPLICABLE YET-Build a GoogleSignInClient with the options specified by gso.
        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_in_button.setOnClickListener()
        {

        }


        //Initialize array list
        var items = arrayListOf<String>()
        //Initialize adapter
        var adapt = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items)

        //Implement add button
        add.setOnClickListener()
        {
            items.add(editText.text.toString())
            listView.adapter = adapt
            adapt.notifyDataSetChanged()
            editText.text.clear()
        }

        //Implement delete button
        delete.setOnClickListener()
        {
            //Use SparseBooleanArray to iterate over list and find index
            val pos: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (pos.get(item))
                {
                    adapt.remove(items.get(item))
                }
                item--
            }
            pos.clear()
            adapt.notifyDataSetChanged()
        }

        //Implement clear button
        clear.setOnClickListener()
        {
            items.clear()
            adapt.notifyDataSetChanged()
        }


    }
    override fun onStart()
    {
        super.onStart()
        //Toast.makeText(applicationContext, "Now onStart() calls", Toast.LENGTH_LONG)
        //  .show() //onStart Called
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        //If account is null, display sign in button
        // IMPLEMENT updateUI(account)
    }
}
