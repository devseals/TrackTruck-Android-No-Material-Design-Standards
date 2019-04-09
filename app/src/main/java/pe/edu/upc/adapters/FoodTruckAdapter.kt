package pe.edu.upc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import pe.edu.upc.models.Foodtruck
import pe.edu.upc.R
import java.util.*

class FoodtruckAdapter(context:Context, foodtrucks: List<Foodtruck>) : BaseAdapter(){

    val context = context
    val foodtrucks = foodtrucks

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val foodtruckView: View
        val holder: ViewHolder

        if(convertView == null) {
            holder = ViewHolder()
            foodtruckView = LayoutInflater.from(context).inflate(R.layout.card_foodtruck, null)
            holder.foodtruckNameLbl = foodtruckView.findViewById(R.id.foodtruckNameLbl)
            holder.avgCostLbl = foodtruckView.findViewById(R.id.avgCostLbl)
            holder.foodTypeLbl = foodtruckView.findViewById(R.id.foodTypeLbl)
            holder.foodtruckNameTextview = foodtruckView.findViewById(R.id.foodtruckNameTextview)
            holder.avgCostTextView = foodtruckView.findViewById(R.id.avgCostTextView)
            holder.foodTypeTextView = foodtruckView.findViewById(R.id.foodTypeTextview)
            holder.foodtruckImageview = foodtruckView.findViewById(R.id.foodtruckImageview)
            foodtruckView.tag=holder
        }else{
            holder = convertView.tag as ViewHolder
            foodtruckView = convertView
        }

        val random = Random()
        var randomInt = random.nextInt(5 + 1)

        val foodtruck = foodtrucks[position]

        //aqui falta cadenar datos del FT
        //holder.foodtruckNameTextview.text = foodtruck.name

        val resourseId = context.resources.getIdentifier("ft"+randomInt.toString(),"drawable",context.packageName)
        holder.foodtruckImageview?.setImageResource(resourseId)

        return foodtruckView
    }

    override fun getItem(position: Int): Any {
        return foodtrucks[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return foodtrucks.count()
    }

    private class ViewHolder{
        var foodtruckNameLbl : TextView? = null
        var avgCostLbl : TextView? = null
        var foodTypeLbl : TextView? = null
        var foodtruckNameTextview : TextView? = null
        var avgCostTextView : TextView? = null
        var foodTypeTextView : TextView? = null
        var foodtruckImageview : ImageView? = null
    }

}