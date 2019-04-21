package pe.edu.upc.services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import pe.edu.upc.activities.ReviewsActivity
import pe.edu.upc.constants.GET_FOODTRUCK
import pe.edu.upc.constants.GET_FOODTRUCKS
import pe.edu.upc.fragments.FoodtrucksFragment
import pe.edu.upc.models.Foodtruck
import pe.edu.upc.models.Review
import java.lang.reflect.Method

class FoodtrucksService{

    fun downloadFoodtrucks(context: Context, listener: FoodtrucksFragment.TrucksDownloaded) : ArrayList<Foodtruck>{

        val getFoodTrucks = java.util.ArrayList<Foodtruck>()

        val getFoodtrucksRequest = object: JsonObjectRequest(
            Method.GET,
            GET_FOODTRUCKS, null,
            Response.Listener
            { response ->
                System.out.println(response.toString())
                try {

                    val responseFoodtrucks = response.getJSONArray("Foodtrucks")

                    for(i in 0 until responseFoodtrucks.length() ){

                        var foodtruck : JSONObject = responseFoodtrucks.getJSONObject(i)
                        val id:Int= foodtruck.getInt("foodtruck_id")
                        val latitude: Double = foodtruck.getDouble("latitude")
                        val longitude:Double = foodtruck.getDouble("longitude")
                        val name: String = foodtruck.getString("name")
                        val food_type: String= foodtruck.getString("food_type")
                        val owner_id:Int= foodtruck.getJSONObject("owners").getInt("owner_id")
                        val avg_price:Double = foodtruck.getDouble("avg_price")

                        val newFoodtruck = Foodtruck(id,latitude,longitude,name,food_type,owner_id,avg_price)
                        getFoodTrucks.add(newFoodtruck)

                    }
                }catch (e: JSONException) {
                    Log.d("ERROR", e.localizedMessage)
                }

                listener.success(true)

            },
            Response.ErrorListener
            { error->
                Log.d("ERROR","Could not find $error")
            }
        ){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

        }

        Volley.newRequestQueue(context).add(getFoodtrucksRequest)
        return getFoodTrucks
    }

    fun downloadReviews(context: Context, foodtruckId: Int, listener: ReviewsActivity.ReviewsDownloaded) : ArrayList<Review>{

        val getReviews = ArrayList<Review>()

        val getReviewsRequest = object : JsonObjectRequest(Method.GET,
            GET_FOODTRUCK + foodtruckId.toString(),
            null, Response.Listener {
            response ->
            try {
                val responseReviews = response.getJSONObject("Foodtrucks").getJSONArray("Reviews")

                for (i in 0 until responseReviews.length()){

                    val review : JSONObject = responseReviews.getJSONObject(i)
                    val review_id = review.getInt("review_id")
                    val user_id = review.getJSONObject("users").getInt("user_id")
                    val name: String = review.getJSONObject("users").getString("name")
                    val username:String = review.getJSONObject("users").getString("username")
                    val phone_number:String = review.getJSONObject("users").getString("phone_number")
                    val foodtruck_id = review.getInt("foodtruck_id")
                    val content:String = review.getString("content")

                    val newReview = Review(review_id, user_id,name,username,phone_number,foodtruck_id,content)
                    getReviews.add(newReview)
                }

            }catch (e:JSONException){
                Log.d("ERROR", e.localizedMessage)
            }

            listener.success(true)

        }, Response.ErrorListener {
            error->
                Log.d("ERROR","Could not find $error")

        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }

        Volley.newRequestQueue(context).add(getReviewsRequest)

        return getReviews

    }

}