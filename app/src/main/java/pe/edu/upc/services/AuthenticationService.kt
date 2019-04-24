package pe.edu.upc.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import pe.edu.upc.constants.*

class AuthenticationService {

    var authtoken=""

    fun registerUser(context: Context,userName:String, name:String, phoneNumber:String , password:String){

        val jsonBody = JSONObject()
        jsonBody.put("name", name)
        jsonBody.put("password", password )
        jsonBody.put("username",userName)
        jsonBody.put("phone_number", phoneNumber)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, REGISTER_USER,null, Response.Listener {
            response ->

            try {

            }catch (e : JSONException){
                Log.d("ERROR", e.localizedMessage)
            }

        }, Response.ErrorListener {
                error ->
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

    fun registerOwner(context: Context, name: String, userName: String, password: String, ruc: String){

        val jsonBody = JSONObject()
        jsonBody.put("ruc", ruc)
        jsonBody.put("name", name)
        jsonBody.put("password", password )
        jsonBody.put("username",userName)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, REGISTER_OWNER, null, Response.Listener {
            response ->

            try {

            }catch (e : JSONException){
                Log.d("ERROR", e.localizedMessage)
            }

        }, Response.ErrorListener {
                error ->
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

    fun logUser(context: Context, userName:String, password:String){

        val jsonBody = JSONObject()
        jsonBody.put("password", password )
        jsonBody.put("username",userName)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, LOG_USER,null, Response.Listener {
                response ->
                authtoken = response.getString("Token")
                DataServiceU.authToken = response.getString("Token")
                DataServiceU.id = response.getJSONObject("User").getInt("user_id")
                DataServiceU.name = response.getJSONObject("User").getString("name")
                DataServiceU.isLogged = true
            try {

            }catch (e : JSONException){
                Log.d("ERROR", e.localizedMessage)
            }

        }, Response.ErrorListener {
                error ->
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

    fun logOwner(context: Context, userName:String, password:String){

        val jsonBody = JSONObject()
        jsonBody.put("password", password )
        jsonBody.put("username",userName)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, LOG_OWNER,null, Response.Listener {
                response ->
                authtoken = response.getString("Token")
                DataServiceO.isLogged = true
                DataServiceO.id = response.getJSONObject("Owner").getInt("owner_id")
                DataServiceO.name = response.getJSONObject("Owner").getString("name")
                DataServiceO.authToken = response.getString("Token")
            try {

            }catch (e : JSONException){
                Log.d("ERROR", e.localizedMessage)
            }

        }, Response.ErrorListener {
                error ->
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

    fun registerEmployee(context: Context, name:String, userName: String, password: String){

        val jsonBody = JSONObject()
        jsonBody.put("name",name)
        jsonBody.put("username", userName)
        jsonBody.put("password", password)
        jsonBody.put("owner_id", DataServiceO.id)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, REGISTER_EMPLOYEE, null,
            Response.Listener {
                response->
                    try {

                    }catch (e: JSONException){
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

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Authorization", "Bearer "+DataServiceO.authToken)
                return headers
            }
        }
        Volley.newRequestQueue(context).add(createRequest)
    }

    fun logEmployee(context: Context, userName:String, password:String){

        val jsonBody = JSONObject()
        jsonBody.put("password", password )
        jsonBody.put("username",userName)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(Method.POST, LOG_EMPLOYEE,null, Response.Listener {
                response ->

            try {

            }catch (e : JSONException){
                Log.d("ERROR", e.localizedMessage)
            }

        }, Response.ErrorListener {
                error ->
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

}