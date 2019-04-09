package pe.edu.upc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recycler_foodtruck.*
import pe.edu.upc.R
import pe.edu.upc.adapters.FoodTruckRecycleAdapter
import pe.edu.upc.models.DataService
import pe.edu.upc.models.Foodtruck

class FoodTrucksListActivity : AppCompatActivity() {

    //lateinit var adapter: ArrayAdapter<Foodtruck>
    lateinit var adapter: FoodTruckRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_trucks_list)


        //   adapter = FoodtruckAdapter(this, TU LISTA DE DATOS)
        adapter = FoodTruckRecycleAdapter(this, DataService.foodtrucks){
              foodtruck->
        //          val foodtruckIntent = Intent(this, otraactividad::class:java)
        //          foodtruckIntent.putExtra(EXTRA_FOODTRUCK, foodtruck.id)
        // EN EL OTRO GET EXTRA EXRA_FOODTRUCK
        }
        foodtruckList.adapter = adapter



        val layoutManager = LinearLayoutManager(this)
        foodtruckList.layoutManager=layoutManager
        foodtruckList.setHasFixedSize(true)
    }

}
