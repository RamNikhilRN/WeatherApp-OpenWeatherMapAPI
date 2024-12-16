package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.json.JSONObject

class MainActivity : ComponentActivity() {
var weather_url1 = ""

    var api_key = "************************"


    private lateinit var btVar1: Button
    private lateinit var textView: TextView
    private lateinit var  fusedLocationClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // link the textView in which the
        // temperature will be displayed
        textView = findViewById(R.id.textView)

        btVar1 = findViewById(R.id.btVar1)

        // create an instance of the Fused
        // Location Provider Client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // on clicking this button function to
        // get the coordinates will be called
        btVar1.setOnClickListener {
            // function to find the coordinates
            // of the last location
            checkForPermission()
        }
    }

    private fun checkForPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Request permissions
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            // Permissions are already granted, obtain the location
            obtainLocation()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                // Permission was granted, obtain the location
                obtainLocation()
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }


    @SuppressLint("MissingPermission")
    private fun obtainLocation() {
        // get the last location

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // get the latitude and longitude
                // and create the http URL
                if (location != null) {
                    weather_url1 = "https://api.openweathermap.org/data/2.5/weather?lat=${location.latitude}&lon=${location.longitude}&units=metric&appid=${api_key}"
                }
                // this function will
                // fetch data from URL
                getTemp()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Location Permission not granted", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getTemp() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = weather_url1

        // Request a string response
        // from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url, { response ->
            // get the JSON object
            val obj = JSONObject(response)

            // Getting the temperature readings from response
            val main: JSONObject = obj.getJSONObject("main")
            val temperature = main.getString("temp")
            println(temperature)

            // Getting the city name
            val city = obj.getString("name")
            println(city)

            // set the temperature and the city
            // name using getString() function
            textView.text = "$temperature°C in $city"
            System.out.println(obj.toString())
        },
            // In case of any error
            { textView.text = "That didn't work!" })
        queue.add(stringReq)
    }

}
