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
import pe.edu.upc.models.Foodtruck
import pe.edu.upc.services.FoodtrucksService
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

    var trucks = ArrayList<Foodtruck>()
    val foodtruckService = FoodtrucksService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_foodtrucks, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val listener = object : TrucksDownloaded{
            override fun success(success: Boolean) {
                if (success){
                    setUpRecyler()
                }
            }
        }
        setUpRecyler()
        trucks=foodtruckService.downloadFoodtrucks(view.context, listener)

    }

    fun setUpRecyler(){
        foodtruckList.apply {
            foodtruckList.adapter = FoodTruckRecycleAdapter(trucks)
            foodtruckList.layoutManager=LinearLayoutManager(this.context)
            foodtruckList.setHasFixedSize(true)
        }
    }

    interface TrucksDownloaded{

        fun success(success: Boolean)

    }
}
