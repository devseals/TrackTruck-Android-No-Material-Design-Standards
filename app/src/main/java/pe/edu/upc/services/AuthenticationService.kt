package pe.edu.upc.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import pe.edu.upc.constants.LOG_OWNER
import pe.edu.upc.constants.LOG_USER
import pe.edu.upc.constants.REGISTER_OWNER
import pe.edu.upc.constants.REGISTER_USER

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

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Authorization", "Bearer $authtoken")
                return headers
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

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Authorization", "Bearer $authtoken")
                return headers
            }
        }
        Volley.newRequestQueue(context).add(createRequest)
    }

}