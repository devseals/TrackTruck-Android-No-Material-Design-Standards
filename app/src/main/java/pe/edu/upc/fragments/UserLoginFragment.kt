package pe.edu.upc.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_login.*

import pe.edu.upc.R
import pe.edu.upc.activities.CreateReviewActivity
import pe.edu.upc.services.AuthenticationService
import pe.edu.upc.services.DataServiceU

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class UserLoginFragment : Fragment() {

    var authService = AuthenticationService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        material_text_button.setOnClickListener {
                view->
            loginUser(view)
        }
    }

    fun loginUser(view: View){
        authService.logUser(view.context, userUserTxt.editText?.text.toString(), userPassTxt.editText?.text.toString())
        if (DataServiceU.isLogged){
            val intent = Intent(context, CreateReviewActivity::class.java)
            intent.putExtra("foodtruckId", activity?.intent?.getIntExtra("foodtruckId",0))
            startActivity(intent)
        }
    }
}
