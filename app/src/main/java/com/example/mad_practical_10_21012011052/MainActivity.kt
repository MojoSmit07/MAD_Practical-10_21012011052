package com.example.mad_practical_10_21012011052

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
                                runOnUiThread { getPersonDetailsFromJson(data) }
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


}

