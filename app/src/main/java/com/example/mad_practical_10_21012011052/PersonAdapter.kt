package com.example.mad_practical_10_21012011052
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PersonAdapter(context: Context, val PersonArray: ArrayList<Person>):ArrayAdapter<Person>(context, 0, PersonArray) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.person_item, parent, false)

        view.findViewById<TextView>(R.id.textView).text = PersonArray[position].name
        view.findViewById<TextView>(R.id.textView2).text = PersonArray[position].phoneNo
        view.findViewById<TextView>(R.id.textView3).text = PersonArray[position].emailid
        view.findViewById<TextView>(R.id.textView4).text = PersonArray[position].address

        view.findViewById<FloatingActionButton>(R.id.location_btn).setOnClickListener {
            Intent(context, MapsActivity::class.java).putExtra("object", PersonArray[position]).apply { context.startActivity(this) }
        }
        return view

    }
}
