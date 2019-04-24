package pe.edu.upc.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.activity_create_foodtruck.*
import pe.edu.upc.R
import pe.edu.upc.services.FoodtrucksService

class CreateFoodtruckActivity : AppCompatActivity() {

    val ftService = FoodtrucksService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_foodtruck)

        addTruckBtn.setOnClickListener {
                view-> createFoodtruck(view)
        }
    }

    fun createFoodtruck(view: View){
        ftService.createTruck(
            view.context,
            truckName.editText?.text.toString(),
            truckType.editText?.text.toString(),
            truckPrice.editText?.text.toString().toDouble(),
            truckLatitude.editText?.text.toString().toDouble(),
            truckLongitude.editText?.text.toString().toDouble()
            )
        startActivity(Intent(view.context, MainActivity::class.java))
    }
}
