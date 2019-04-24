package pe.edu.upc.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_user_register.*

import pe.edu.upc.R
import pe.edu.upc.activities.UserTabActivity
import pe.edu.upc.services.AuthenticationService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UserRegisterFragment : Fragment() {

    val authService = pe.edu.upc.services.AuthenticationService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerUserButton.setOnClickListener { view->
            registerUser(view)
        }
    }

    fun registerUser(view: View){
        authService.registerUser(
            view.context,
            userNameTxt.editText?.text.toString(),
            userRegisterUserTxt.editText?.text.toString(),
            userPhoneTxt.editText?.text.toString(),
            userRegisterPassTxt.editText?.text.toString())

        val intent = Intent(this.context,UserTabActivity::class.java)
        intent.putExtra("foodtruckId", activity?.intent?.getIntExtra("foodtruckId",0))
        startActivity(intent)
    }

}
