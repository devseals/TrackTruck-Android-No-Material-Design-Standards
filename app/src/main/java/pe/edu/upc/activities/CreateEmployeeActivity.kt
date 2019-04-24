package pe.edu.upc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_employee.*
import pe.edu.upc.R

class CreateEmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_employee)

        button5.setOnClickListener {
            view->
            createEmployee(view)
        }
    }

    fun createEmployee(view: View){

    }
}
