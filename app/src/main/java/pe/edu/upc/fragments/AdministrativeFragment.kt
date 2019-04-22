package pe.edu.upc.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import pe.edu.upc.R
import pe.edu.upc.activities.AdministrativeTabActivity
import pe.edu.upc.activities.UserTabActivity
import pe.edu.upc.services.DataService


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AdministrativeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrative, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(DataService.authToken==""){
            val intent = Intent(view.context,AdministrativeTabActivity::class.java)
            startActivity(intent)
        }

    }


}
