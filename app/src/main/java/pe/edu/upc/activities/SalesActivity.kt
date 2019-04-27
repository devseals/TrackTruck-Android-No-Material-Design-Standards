package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sales.*
import pe.edu.upc.R
import pe.edu.upc.adapters.SaleRecycleAdapter
import pe.edu.upc.models.Sale
import pe.edu.upc.services.DataServiceO
import pe.edu.upc.services.FoodtrucksService

class SalesActivity : AppCompatActivity() {

    val ftServ = FoodtrucksService()
    var sales = ArrayList<Sale>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        if(DataServiceO.isLogged==false){
            startActivity(Intent(this, AdministrativeTabActivity::class.java))
        }

        val listener = object: SalesDownloaded{
            override fun success(success: Boolean) {
                if(success){
                    setUpRecycler()
                }
            }
        }

        sales = ftServ.downloadSales(this,listener)

    }

    interface SalesDownloaded{

        fun success(success: Boolean)

    }

    fun setUpRecycler(){
        salesList.apply {
            salesList.adapter= SaleRecycleAdapter(sales)
            salesList.layoutManager=LinearLayoutManager(this.context)
            salesList.setHasFixedSize(true)
        }
    }
}
