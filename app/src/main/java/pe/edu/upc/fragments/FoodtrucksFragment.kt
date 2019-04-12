package pe.edu.upc.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_foodtrucks.*

import pe.edu.upc.R
import pe.edu.upc.adapters.FoodTruckRecycleAdapter
import pe.edu.upc.models.DataService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FoodtrucksFragment : Fragment() {

    lateinit var adapter: FoodTruckRecycleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foodtrucks, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodtruckList.apply {
            foodtruckList.adapter = FoodTruckRecycleAdapter(DataService.foodtrucks)
            foodtruckList.layoutManager=LinearLayoutManager(view.context)
            foodtruckList.setHasFixedSize(true)
        }


    }


}
