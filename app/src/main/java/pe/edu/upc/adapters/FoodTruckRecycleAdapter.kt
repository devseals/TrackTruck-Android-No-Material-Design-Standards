package pe.edu.upc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.models.Foodtruck
import pe.edu.upc.R
import java.util.*


class FoodTruckRecycleAdapter(val context: Context, val foodtrucks: List<Foodtruck>, val itemClick: (Foodtruck)-> Unit) : RecyclerView.Adapter<FoodTruckRecycleAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_foodtruck,parent,false   )
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
    return foodtrucks.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindFootruck(foodtrucks[position], context)
    }

    inner class Holder(itemView: View, val itemClick: (Foodtruck)-> Unit ) : RecyclerView.ViewHolder(itemView) {

        val foodtruckNameTextview = itemView?.findViewById<TextView>(R.id.foodtruckNameTextview)
        val avgCostTextView = itemView?.findViewById<TextView>(R.id.avgCostTextView)
        val foodTypeTextView = itemView?.findViewById<TextView>(R.id.foodTypeTextview)
        val foodtruckImageview = itemView?.findViewById<ImageView>(R.id.foodtruckImageview)

        val random = Random()
        val randomInt = random.nextInt(13) + 1

        fun bindFootruck(foodtruck : Foodtruck, context: Context){
            val resourseId = context.resources.getIdentifier("ft"+randomInt.toString(),"drawable",context.packageName)
            foodtruckImageview?.setImageResource(resourseId)

            //aqui falta cadenar datos del FT
            foodtruckNameTextview?.text = foodtruck.name
            avgCostTextView?.text = foodtruck.avgCost.toString()
            foodTypeTextView?.text = foodtruck.foodType


            itemView.setOnClickListener{itemClick(foodtruck)}
        }
    }
}