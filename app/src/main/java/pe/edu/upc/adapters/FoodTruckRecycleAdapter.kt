package pe.edu.upc.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_foodtruck.view.*
import pe.edu.upc.models.Foodtruck
import pe.edu.upc.R
import pe.edu.upc.activities.MainActivity
import java.util.*


class FoodTruckRecycleAdapter(val foodtrucks: List<Foodtruck> ): RecyclerView.Adapter<FoodTruckRecycleAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_foodtruck,parent,false   )
        return Holder(view)
    }

    override fun getItemCount(): Int {
    return foodtrucks.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindFootruck(foodtrucks[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val foodtruckNameTextview = itemView.findViewById<TextView>(R.id.foodtruckNameTextview)
        val avgCostTextView = itemView.findViewById<TextView>(R.id.avgCostTextView)
        val foodTypeTextView = itemView.findViewById<TextView>(R.id.foodTypeTextview)
        val foodtruckImageview = itemView.findViewById<ImageView>(R.id.foodtruckImageview)

        val random = Random()
        val randomInt = random.nextInt(13) + 1

        var contentItem: CardView = itemView.contentFoodtruck

        fun bindFootruck(foodtruck : Foodtruck){
            val resourseId = itemView.context.resources.getIdentifier("ft"+randomInt.toString(),"drawable",itemView.context.packageName)
            foodtruckImageview?.setImageResource(resourseId)

            //aqui falta cadenar datos del FT
            foodtruckNameTextview?.text = foodtruck.name
            avgCostTextView?.text = foodtruck.avgCost.toString()
            foodTypeTextView?.text = foodtruck.foodType

            contentItem.setOnClickListener{
                val bundle = Bundle()
                bundle.apply {
                    //putString("first_name", item.firstName)
                    //putString("last_name", item.lastName)
                }

                val intent = Intent(it.context, MainActivity::class.java)
                intent.putExtras(bundle)
                it.context.startActivity(intent)
            }


        }
    }
}