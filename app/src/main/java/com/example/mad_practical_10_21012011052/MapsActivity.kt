package com.example.mad_practical_10_21012011052


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mad_practical_10_21012011052.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var lat:Double = 0.0
    private var log:Double = 0.0
    private var title = ""
    private val TAG = "MapsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val obj = intent.getSerializableExtra("Object") as Person
        Log.i(TAG, "onCreate: Object: $obj")
        lat = obj.latitude
        log = obj.longitude
        title = obj.name
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val sydney = LatLng(lat,  log )
        mMap.addMarker(
            MarkerOptions().position(sydney)
                .title(title)
                .snippet(title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img2))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16.0f))
    }
}
