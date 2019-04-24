package pe.edu.upc.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_owner_register.*

import pe.edu.upc.R
import pe.edu.upc.activities.AdministrativeTabActivity
import pe.edu.upc.services.AuthenticationService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OwnerRegisterFragment : Fragment() {

    val authService = AuthenticationService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ownerRegisterBtn.setOnClickListener {
            view->
            registerOwner(view)
        }
    }

    fun registerOwner(view: View){
        authService.registerOwner(view.context,
            ownerNameTxt.editText?.text.toString(),
            ownerRegisterUserTxt.editText?.text.toString(),
            ownerRegisterPassTxt.editText?.text.toString(),
            ownerRucTxt.editText?.text.toString()
        )
        val intent=Intent(context,AdministrativeTabActivity::class.java)
        startActivity(intent)
    }
}
