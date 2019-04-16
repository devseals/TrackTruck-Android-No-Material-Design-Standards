package pe.edu.upc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.food_truck_view.*
import pe.edu.upc.R

class FoodTruckActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0
    private var foodtruckName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_truck_view)

        intent?.extras?.apply {

            foodtruckName = getString("name")
            foodtruckNameViewText.text=foodtruckName
            latitude = getDouble("latitude")
            longitude = getDouble("longitude")
            foodTypeViewText.text=getString("food_type")
            avgCostViewText.text=getDouble("avg_price").toString()
            getInt("foodtruck_id")

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val location = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(location).title("Marcador en $foodtruckName"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL)
        mMap.isTrafficEnabled =  true
        mMap.isIndoorEnabled =   true
        mMap.isBuildingsEnabled = true
        mMap.uiSettings.isZoomControlsEnabled  = true
    }
}
