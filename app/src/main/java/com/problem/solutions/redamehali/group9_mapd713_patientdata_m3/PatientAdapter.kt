package com.problem.solutions.redamehali.group9_mapd713_patientdata_m3

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by Reda Mehali on 11/15/20.
 */
class PatientAdapter(
    context: Context,
    private val dataSource: ArrayList<Patient>) : BaseAdapter() {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val view = inflater.inflate(R.layout.list_view_patients_layout, parent, false)
        val name = view.findViewById<TextView>(R.id.name)
        val age = view.findViewById<TextView>(R.id.age)
        val id = view.findViewById<TextView>(R.id.id)

        val patient = getItem(position) as Patient
        name.text = patient.name
        age.text = patient.age
        id.text = patient.id

        return view
    }
}