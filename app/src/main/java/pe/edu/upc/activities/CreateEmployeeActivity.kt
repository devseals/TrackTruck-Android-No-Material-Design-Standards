package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_employee.*
import pe.edu.upc.R
import pe.edu.upc.services.AuthenticationService
import pe.edu.upc.services.DataServiceO

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
        if(DataServiceO.isLogged) {
            authServ.registerEmployee(
                view.context, employeeName?.editText?.text.toString(),
                employeeUser?.editText?.text.toString(), employeePass?.editText?.text.toString()
            )
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            startActivity(Intent(this, AdministrativeTabActivity::class.java))
        }

    }
}
