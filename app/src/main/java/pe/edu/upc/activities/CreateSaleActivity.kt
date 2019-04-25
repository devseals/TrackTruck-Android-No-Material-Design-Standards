package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_sale.*
import pe.edu.upc.R
import pe.edu.upc.services.FoodtrucksService

class CreateSaleActivity : AppCompatActivity() {

    val ftServ = FoodtrucksService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_sale)

        addSaleBtn.setOnClickListener {
            view->createSale(view)
        }
    }

    fun createSale(view: View){
        ftServ.createSale(view.context, saleRegistryAmount?.editText?.text.toString().toDouble(),
            saleRegistryContent?.editText?.text.toString())
        startActivity(Intent(view.context, MainActivity::class.java))
    }

}
