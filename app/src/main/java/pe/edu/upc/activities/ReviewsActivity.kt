package pe.edu.upc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_reviews.*
import pe.edu.upc.R
import pe.edu.upc.adapters.ReviewRecycleAdapter
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

        val listener = object : ReviewsDownloaded {
            override fun success(success: Boolean) {
                if (success){
                    setUpRecycler()
                }
            }
        }

        setUpRecycler()
        reviews = foodtruckService.downloadReviews(this,foodtruckId, listener)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    interface ReviewsDownloaded{

        fun success(success: Boolean)

    }

    fun setUpRecycler(){
        reviewList.apply {
            reviewList.adapter = ReviewRecycleAdapter(reviews)
            reviewList.layoutManager = LinearLayoutManager(this.context)
            reviewList.setHasFixedSize(true)
        }
    }

}
