package pe.edu.upc.services

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import pe.edu.upc.activities.ReviewsActivity
import pe.edu.upc.activities.SalesActivity
import pe.edu.upc.constants.*
import pe.edu.upc.fragments.FoodtrucksFragment
import pe.edu.upc.models.Foodtruck
import pe.edu.upc.models.Review
import pe.edu.upc.models.Sale
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

                        val foodtruck : JSONObject = responseFoodtrucks.getJSONObject(i)
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
                    val title: String = review.getString("title")
                    val date = review.getString("date")


                    val newReview = Review(review_id, user_id,name,username,phone_number,foodtruck_id,content,title, date)
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

    @SuppressLint("SimpleDateFormat")
    fun createReview(context: Context, foodTruckId: Int, content: String, title:String){

        val jsonBody=JSONObject()
        jsonBody.put("user_id", DataServiceU.id)
        jsonBody.put("foodtruck_id", foodTruckId)
        jsonBody.put("content", content)
        jsonBody.put("title",title)

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())
        jsonBody.put("date",currentDate)

        val requestBody=jsonBody.toString()

        val reviewRequest = object: JsonObjectRequest(Method.POST, REGISTER_REVIEW, null,Response.Listener {
            response ->
            try {

            }catch (e: JSONException){
                Log.d("ERROR", e.localizedMessage)
            }
        },Response.ErrorListener {
            error->
            Log.d("ERROR"," Could not find $error")
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }
        Volley.newRequestQueue(context).add(reviewRequest)
    }

    fun createTruck(context: Context, name: String, food_type: String, avg_price: Double, latitude: Double, longitude:Double){

        val jsonBody = JSONObject()
        jsonBody.put("name", name)
        jsonBody.put("owner_id", DataServiceO.id)
        jsonBody.put("food_type", food_type)
        jsonBody.put("latitude", latitude)
        jsonBody.put("longitude", longitude)
        jsonBody.put("avg_price", avg_price)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, REGISTER_FOODTRUCK,null ,Response.Listener {
            response ->
            try {

            }catch (e:JSONException){
                Log.d("ERROR", e.localizedMessage)
            }
        }, Response.ErrorListener {
            error->
            Log.d("ERROR", "Could not find $error")
        }){

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(createRequest)

    }

    fun downloadSales(context: Context, listener: SalesActivity.SalesDownloaded): ArrayList<Sale>{

        val getSales = ArrayList<Sale>()

        val getSalesRequest = object : JsonObjectRequest(Method.GET,
            GET_OWNER_SALES+DataServiceO.id.toString(),
            null,
            Response.Listener {
                response ->
                try {

                    val responseSales = response.getJSONArray("SalesRecords")

                    for(i in 0 until responseSales.length()){

                        val employeeName = responseSales.getJSONObject(i).getJSONObject("employees").getString("name")
                        val date=responseSales.getJSONObject(i).getString("date")
                        val amount=responseSales.getJSONObject(i).getDouble("value")
                        val content=responseSales.getJSONObject(i).getString("content")
                        val newSale= Sale(employeeName,content,date,amount)
                        getSales.add(newSale)
                    }

                }catch (e:JSONException){
                    Log.d("ERROR", e.localizedMessage)
                }

                listener.success(true)

            }, Response.ErrorListener {
                error->
                Log.d("ERROR", "Could not find $error")
            }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }

        Volley.newRequestQueue(context).add(getSalesRequest)
        return getSales
    }

    @SuppressLint("SimpleDateFormat")
    fun createSale(context: Context, value: Double, content: String){

        val jsonBody = JSONObject()
        jsonBody.put("employee_id", DataServiceE.id)
        jsonBody.put("value",value)
        jsonBody.put("content",content)

        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        jsonBody.put("date",currentDate)

        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, REGISTER_SALE, null,
            Response.Listener {
                response ->
                try {

                }catch (e:JSONException){
                    Log.d("ERROR", e.localizedMessage)
                }
            },Response.ErrorListener {
                error->
                Log.d("ERROR", "Could not find $error")
            }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Authorization", "Bearer "+DataServiceE.authToken)
                return headers
            }
        }

        Volley.newRequestQueue(context).add(createRequest)

    }

}