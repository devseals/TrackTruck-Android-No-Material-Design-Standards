package pe.edu.upc.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_employee_login.*

import pe.edu.upc.R
import pe.edu.upc.services.AuthenticationService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EmployeeLoginFragment : Fragment() {

    val authServ = AuthenticationService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logineEmployeeBtn.setOnClickListener {
            view->loginEmployee(view)
        }
    }

    fun loginEmployee(view: View){
    authServ.logEmployee(view.context,
        employeeUserTxt?.editText?.text.toString(),
        employeePassTxt?.editText?.text.toString())
        //TODO IR A OTRA ACTIVIDAD PARA EMPLEADOS
    }

}
