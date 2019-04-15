package pe.edu.upc.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_foodtrucks.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import pe.edu.upc.R
import pe.edu.upc.adapters.FoodTruckRecycleAdapter
import pe.edu.upc.constants.GET_FOODTRUCKS
import pe.edu.upc.models.DataService
import pe.edu.upc.models.Foodtruck
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FoodtrucksFragment : Fragment() {

    val getFoodTrucks = ArrayList<Foodtruck>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foodtrucks, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

                        foodtruckList.apply {
                            foodtruckList.adapter = FoodTruckRecycleAdapter(getFoodTrucks)
                            foodtruckList.layoutManager=LinearLayoutManager(view.context)
                            foodtruckList.setHasFixedSize(true)
                        }

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

        Volley.newRequestQueue(view.context).add(getFoodtrucksRequest)

    }


}
