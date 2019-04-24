package pe.edu.upc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_reviews.*
import pe.edu.upc.R
import pe.edu.upc.adapters.ReviewRecycleAdapter
import pe.edu.upc.fragments.FoodtrucksFragment
import pe.edu.upc.models.Review
import pe.edu.upc.services.DataServiceU
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

        reviews = foodtruckService.downloadReviews(this,foodtruckId, listener)

        fab.setOnClickListener { view ->
            if(DataServiceU.authToken=="") {
                
                val intent = Intent(this, UserTabActivity::class.java)
                intent.putExtra("foodtruckId", foodtruckId)
                startActivity(intent)
            }else if(DataServiceU.authToken!=""){
                val intent2 = Intent(this, CreateReviewActivity::class.java)
                intent2.putExtra("foodtruckId", foodtruckId)
                startActivity(intent2)
            }
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
