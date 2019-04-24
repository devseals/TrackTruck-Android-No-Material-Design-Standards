package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_review.*
import pe.edu.upc.R
import pe.edu.upc.services.AuthenticationService
import pe.edu.upc.services.FoodtrucksService

class CreateReviewActivity : AppCompatActivity() {

    var foodtruckId:Int = 0
    val ftServ = FoodtrucksService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_review)

        foodtruckId= intent.getIntExtra("foodtruckId",0)

        button4.setOnClickListener {
            view->
            createReview(view)
        }
    }

    fun createReview(view: View){
        ftServ.createReview(view.context,
            foodtruckId,reviewContent.editText?.text.toString(),
            reviewTitle.editText?.text.toString())
        val intent= Intent(view.context,ReviewsActivity::class.java)
        intent.putExtra("foodtruckId", foodtruckId)
        startActivity(intent)
    }
}
