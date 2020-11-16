package com.problem.solutions.redamehali.group9_mapd713_patientdata_m3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var listOfPatientsView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfPatientsButton = findViewById<Button>(R.id.listOfPatientsButton)
        listOfPatientsView = findViewById(R.id.listOfPatientsView)
        listOfPatientsButton.setOnClickListener {
            establishConnection()
            Toast.makeText(this, "This is the list of all patients", Toast.LENGTH_SHORT).show()
        }
    }

    private fun establishConnection() {
        // Instantiate the RequestQueue.
        // Instantiate the RequestQueue.
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val patientsUrl = "https://zv-rest-server.herokuapp.com/users"

        // Request a string response from the provided URL.
        val stringRequest = JsonArrayRequest(Request.Method.GET, patientsUrl, null,
                { response ->
                    buildListOfPatients(response)
                    Log.d(TAG, "Request is successful")
                },
                { Log.d(TAG, "Error, request did not work") })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun buildListOfPatients(response : JSONArray) {
        val patientList : ArrayList<Patient> = ArrayList()
        for (i in 0 until response.length()) {
            val name = (response.get(i) as JSONObject).getString("name")
            val age = (response.get(i) as JSONObject).getString("age")
            val id = (response.get(i) as JSONObject).getString("_id")

            val patient = Patient(name, age, id)
            patientList.add(patient)
        }

        val adapter = PatientAdapter(this, patientList);
        listOfPatientsView.adapter = adapter;
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}