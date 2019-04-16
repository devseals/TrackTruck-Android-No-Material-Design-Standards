package pe.edu.upc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.upc.R
import pe.edu.upc.fragments.FoodtrucksFragment
import pe.edu.upc.models.Review
import pe.edu.upc.services.FoodtrucksService

class ReviewsActivity : AppCompatActivity() {

    private var foodtruckId:Int=0
    private var reviews = ArrayList<Review>()
    val foodtruckService = FoodtrucksService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        foodtruckId= intent.getIntExtra("foodtruckId",0)

        var listener = object : ReviewsDownloaded {
            override fun success(success: Boolean) {
                if (success){
                System.out.println(reviews)
                }
            }
        }

        reviews = foodtruckService.downloadReviews(this,foodtruckId, listener)

    }

    interface ReviewsDownloaded{

        fun success(success: Boolean)

    }

}
