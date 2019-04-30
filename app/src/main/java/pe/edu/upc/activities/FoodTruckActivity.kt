package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.food_truck_view.*


class FoodTruckActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0
    private var foodtruckName : String = ""
    private var foodtruckId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pe.edu.upc.R.layout.food_truck_view)
        intent?.extras?.apply {

            foodtruckName = getString("name")
            foodtruckNameViewText.text=foodtruckName
            latitude = getDouble("latitude")
            longitude = getDouble("longitude")
            foodTypeViewText.text=getString("food_type")
            avgCostViewText.text=getDouble("avg_price").toString()
            foodtruckId= getInt("foodtruck_id")

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(pe.edu.upc.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setTitle(foodtruckName)
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

        val zoom = CameraUpdateFactory.zoomTo(15f)

        mMap.animateCamera(zoom)

        mMap.isTrafficEnabled =  true
        mMap.isIndoorEnabled =   true
        mMap.isBuildingsEnabled = true
        mMap.uiSettings.isZoomControlsEnabled  = true
    }

    fun showReviews(view: View){
        val intentReviews = Intent(this,ReviewsActivity::class.java)
        intentReviews.putExtra("foodtruckId", foodtruckId)
        startActivity(intentReviews)
    }
}
