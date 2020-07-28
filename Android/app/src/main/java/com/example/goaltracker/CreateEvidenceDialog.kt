package com.example.goaltracker

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
class CreateEvidenceDialog: DialogFragment() {

    private lateinit var evidenceContext: EditText
    private lateinit var evidenceImageView: ImageView
    private var photoSelectedUriString: String = ""
    private lateinit var evidencePhotoButton: Button
    private lateinit var evidenceHabitSpinner: Spinner

    private val RC_PHOTO_PICKER = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.create_evidence_dialog,container,false)
        evidenceHabitSpinner = rootView.findViewById(R.id.evidence_habit_spinner)
        evidenceContext = rootView.findViewById(R.id.evidence_context_edit_text)
        val saveEvidenceButton: Button = rootView.findViewById(R.id.save_create_evidence_button)
        saveEvidenceButton.setOnClickListener {
            if (validInputs()){
                val intent = Intent()
                intent.putExtra("evidenceContext", evidenceContext.text.toString())
                intent.putExtra("photoSelectedUriString",photoSelectedUriString)
                intent.putExtra("evidenceHabit",evidenceHabitSpinner.selectedItem as String)
                targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK,intent)
                dismiss()
            }
        }

        val cancelEvidenceButton: Button = rootView.findViewById(R.id.cancel_create_evidence_button)
        cancelEvidenceButton.setOnClickListener{
            dismiss()
        }

        evidenceImageView = rootView.findViewById(R.id.evidence_image_selected_image_view)
        evidencePhotoButton = rootView.findViewById(R.id.evidence_Photo_button)
        evidencePhotoButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(
                Intent.createChooser(intent, "Complete Action using"),
                RC_PHOTO_PICKER
            )
        }



//        // create an arrayAdapter using the string array and a default spinner layout
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // apply the adapter to the the spinner
//            evidenceGoalSpinner.adapter = adapter
//        }

        return rootView
    }

    private fun validInputs(): Boolean {
        var flag = true
        if(evidenceContext.text.toString().isEmpty()){
            evidenceContext.error = "Enter valid input"
            flag = false
        }
        if(evidenceHabitSpinner.selectedItem == null){
            Toast.makeText(context,"You have to create a habit first!",Toast.LENGTH_LONG).show()
            flag = false
        }
        return flag
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == RC_PHOTO_PICKER) {
            val photoSelectedUri = data!!.data
            photoSelectedUriString = photoSelectedUri.toString()
            Glide.with(this)
                .load(photoSelectedUri)
                .into(evidenceImageView)
        }
    }
}