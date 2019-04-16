package pe.edu.upc.services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import pe.edu.upc.constants.GET_FOODTRUCKS
import pe.edu.upc.fragments.FoodtrucksFragment
import pe.edu.upc.models.Foodtruck

class FoodtrucksService{

    fun downloadFoodtrucks(context: Context, listener: FoodtrucksFragment.TrucksDownloaded) : ArrayList<Foodtruck>{

        val getFoodTrucks = java.util.ArrayList<Foodtruck>()

        val getFoodtrucksRequest = object: JsonObjectRequest(
            Method.GET,
            GET_FOODTRUCKS, null,
            Response.Listener
            { response ->
                System.out.println(response.toString())
                try {

                    val responseFoodtrucks = response.getJSONArray("Foodtrucks")

                    for(i in 0 until responseFoodtrucks.length() ){

                        var foodtruck : JSONObject = responseFoodtrucks.getJSONObject(i)
                        val id:Int= foodtruck.getInt("foodtruck_id")
                        val latitude: Double = foodtruck.getDouble("latitude")
                        val longitude:Double = foodtruck.getDouble("longitude")
                        val name: String = foodtruck.getString("name")
                        val food_type: String= foodtruck.getString("food_type")
                        val owner_id:Int= foodtruck.getJSONObject("owners").getInt("owner_id")
                        val avg_price:Double = foodtruck.getDouble("avg_price")

                        val newFoodtruck = Foodtruck(id,latitude,longitude,name,food_type,owner_id,avg_price)
                        getFoodTrucks.add(newFoodtruck)

                    }
                }catch (e: JSONException) {
                    Log.d("ERROR", e.localizedMessage)
                }

                listener.success(true)

            },
            Response.ErrorListener
            { error->
                Log.d("ERROR","Could not find $error")
            }
        ){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }

        Volley.newRequestQueue(context).add(getFoodtrucksRequest)
        return getFoodTrucks
    }

}