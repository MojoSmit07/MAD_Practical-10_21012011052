package com.example.mad_practical_10_21012011052
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.util.ArrayList

class PersonAdapter(context:Context,  val Personarray : ArrayList<Person>): ArrayAdapter<Person>(context,0,Personarray) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        view.findViewById<TextView>(R.id.name_textView).text = Personarray[position].name
        view.findViewById<TextView>(R.id.number_textView).text= Personarray[position].phoneNo
        view.findViewById<TextView>(R.id.email_textView).text= Personarray[position].emailId
        view.findViewById<TextView>(R.id.address_textView).text= Personarray[position].address
        view.findViewById<MaterialButton>(R.id.location_btn).setOnClickListener {
            Intent(context, MapsActivity::class.java).putExtra("Object",Personarray[position]).apply { context.startActivity(this) }
        }
        return view
    }
}

