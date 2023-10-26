package com.example.mad_practical_10_21012011052

import HttpRequest
import android.app.Person
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val floatingBt=findViewById<FloatingActionButton>(R.id.floatingBt)

        floatingBt.setOnClickListener {
            SendDatatoListview()
            Intent(this,MapsActivity::class.java).apply { startActivity(this) }
        }


    }
    private fun getPersonDetailsFromJson(sJson: String?) {
        val personList = ArrayList<contect>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = contect(jsonObject)
                personList.add(person)
            }
            val personListview = findViewById<ListView>(R.id.Listview1)
            personListview.adapter = contactAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }
    fun SendDatatoListview(){
//        val personListview = findViewById<ListView>(R.id.Listview1)
//        val personList= arrayListOf(
//        contect("1","palak","palak@gmail.com","1201333556","xyz",1.5,4.2),
//        contect("2","sagar","sagar@gmail.com","1201333556","xyz",1.5,4.2),
//        contect("3","shreya","shreya@gmail.com","1201333556","xyz",1.5,4.2),
//        contect("4","krina","krina@gmail.com","1201333556","xyz",1.5,4.2)
//                )
//
//        personListview.adapter=contactAdapter(this , personList)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = HttpRequest().makeServiceCall(
                    "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                    "rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1")
                withContext(Dispatchers.Main) {
                    try {
                        if(data != null)
                            runOnUiThread{getPersonDetailsFromJson(data)}
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}