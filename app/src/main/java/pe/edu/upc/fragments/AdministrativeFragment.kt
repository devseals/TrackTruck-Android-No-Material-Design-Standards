package pe.edu.upc.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_administrative.*

import pe.edu.upc.R
import pe.edu.upc.activities.AdministrativeTabActivity
import pe.edu.upc.activities.CreateEmployeeActivity
import pe.edu.upc.activities.CreateFoodtruckActivity
import pe.edu.upc.activities.SalesActivity
import pe.edu.upc.services.DataServiceO


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AdministrativeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if(DataServiceO.authToken=="") {
            val intent = Intent(this.context, AdministrativeTabActivity::class.java)
            startActivity(intent)
        }

        return inflater.inflate(R.layout.fragment_administrative, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        salesBtn.setOnClickListener {
            view->viewSales()
        }

        truckBtn.setOnClickListener {
            view->addTruck()
        }

        employeeBtn.setOnClickListener {
            view->addEmployee()
        }
    }

    fun viewSales(){
        val intent = Intent(context, SalesActivity::class.java)
        startActivity(intent)
    }

    fun addTruck(){
        val intent = Intent(context, CreateFoodtruckActivity::class.java)
        startActivity(intent)
    }

    fun addEmployee(){
        val intent = Intent(context, CreateEmployeeActivity::class.java)
        startActivity(intent)
    }

}
