package com.example.mad_practical_10_21012011052

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

        val floatingBt = findViewById<FloatingActionButton>(R.id.floatingBt)
        floatingBt.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val data = HttpRequest().makeServiceCall(
                        "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                        "dchj8v1b6qqdjzbqood1jgpachyfzlw58r540gru"
                    )
                    withContext(Dispatchers.Main) {
                        try {
                            if (data != null)
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


        fun setArraytoListView() {
            val personListView = findViewById<ListView>(R.id.Listview1)
            val Array = arrayListOf<com.example.mad_practical_10_21012011052.Person>(
                Person("1", "Smit", "nklnkl", "8511683205", "nllkl", 90.90, 909.90),
                Person("2", "dvvdd", "nklnkl", "nklnkl", "nllkl", 90.90, 909.90),
                Person("3", "nldvsdnk", "nklnkl", "nklnkl", "nllkl", 90.90, 909.90),
                Person("4", "nldvdvnk", "nklnkl", "nklnkl", "nllkl", 90.90, 909.90),
                Person("5", "scsnlnk", "nklnkl", "nklnkl", "nllkl", 90.90, 909.90)
            )
            personListView.adapter = PersonAdapter(this, Array)
        }
    }

    private fun getPersonDetailsFromJson(sJson: String) {
        val personList = ArrayList<Person>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Person(jsonObject)
                personList.add(person)
            }
            val Personlistviewe=findViewById<ListView>(R.id.Listview1)
            Personlistviewe.adapter = PersonAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }



}

