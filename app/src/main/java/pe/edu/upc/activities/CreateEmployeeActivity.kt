package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_employee.*
import pe.edu.upc.R
import pe.edu.upc.services.AuthenticationService

class CreateEmployeeActivity : AppCompatActivity() {

    val authServ = AuthenticationService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_employee)

        button5.setOnClickListener {
            view->
            createEmployee(view)
        }
    }

    fun createEmployee(view: View){
        authServ.registerEmployee(view.context, employeeName?.editText?.text.toString() ,
            employeeUser?.editText?.text.toString(), employeePass?.editText?.text.toString())
        startActivity(Intent(this, MainActivity::class.java))
    }
}
