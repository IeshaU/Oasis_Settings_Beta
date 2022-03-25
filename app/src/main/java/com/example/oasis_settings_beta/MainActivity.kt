package com.example.oasis_settings_beta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.oasis_settings_beta.databinding.ActivityMainBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    //   setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

        val saveButton = binding.saveButton

        saveButton.setOnClickListener {  }
            val firstName = binding.inputFirstName.text.toString()
            val lastName = binding.inputLastName.text.toString()
            val emailAddress = binding.inputEmailAddress.text.toString()
            val topDestination1 = binding.inputDestination1.text.toString()
            val topDestination2 = binding.inputDestination2.text.toString()
            val topDestination3 = binding.inputDestination3.text.toString()

            saveFireStore(firstName, lastName, emailAddress,topDestination1,topDestination2, topDestination3)

        }

    }

    fun saveFireStore(firstname: String, lastname: String, emailaddress: String, destination1: String, destination2: String, destination3: String ) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["firstName"] = firstname
        user["lastName"] = lastname
        user["emailAddress"] = emailaddress
        user["topDestination1"] = destination1
        user["topDestination2"] = destination2
        user["topDestination3"] = destination3

        db.collection("users")
            .add(user)
//            .addOnSuccessListener {
//                Toast.makeText(MainActivity.this, "record added successfully", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            .addOnFailureListener {
//                Toast.makeText(this@MainActivity, "record failed to add", Toast.LENGTH_SHORT).show()
//            }
        readFireStoreData()
    }
    fun readFireStoreData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnCompleteListener{

                val result: StringBuffer = StringBuffer()
                if (it.isSuccessful){
                    for(document in it.result!!){
                        result.append(document.data.getValue("firstName")).append(" ")
                            .append(document.data.getValue("lastName")).append(" ")
                            .append(document.data.getValue("emailAddress")).append(" ")
                            .append(document.data.getValue("topDestination1")).append(" ")
                            .append(document.data.getValue("topDestination2")).append(" ")
                            .append(document.data.getValue("topDestination3")).append("\n\n")
                    }

                }

            }

    }

