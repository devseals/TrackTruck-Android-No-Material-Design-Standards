package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_food_trucks_list.*
import kotlinx.android.synthetic.main.recycler_foodtruck.*
import pe.edu.upc.R
import pe.edu.upc.adapters.FoodTruckRecycleAdapter
import pe.edu.upc.constants.EXTRA_FOODTRUCK
import pe.edu.upc.fragments.AdministrativeFragment
import pe.edu.upc.fragments.FoodtrucksFragment
import pe.edu.upc.models.DataService
import pe.edu.upc.models.Foodtruck

class FoodTrucksListActivity : AppCompatActivity() {

    lateinit var adapter: FoodTruckRecycleAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_trucks_list)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        adapter = FoodTruckRecycleAdapter(this, DataService.foodtrucks){
                foodtruck->
                      val foodtruckDetailIntent = Intent(this, FoodTrucksListActivity::class.java)
            //CAMBIAR QUE SEA X NAME
                      foodtruckDetailIntent.putExtra(EXTRA_FOODTRUCK, foodtruck.name)
                      startActivity(foodtruckDetailIntent)
       }
       foodtruckList.adapter = adapter



       val layoutManager = LinearLayoutManager(this)
       foodtruckList.layoutManager=layoutManager
       foodtruckList.setHasFixedSize(true)
    }

    private fun getFragmentFor(item: MenuItem): Fragment {
        when (item.itemId) {
            R.id.navigation_trucks -> {
                return FoodtrucksFragment()
            }
            R.id.navigation_administrative -> {
                return AdministrativeFragment()
            }
        }
        return FoodtrucksFragment()
    }

    private fun navigateTo(item: MenuItem):Boolean{

        item.isChecked = true

        return supportFragmentManager.beginTransaction()
            .replace(R.id.content,getFragmentFor(item))
            .commit() > 0

    }

}
