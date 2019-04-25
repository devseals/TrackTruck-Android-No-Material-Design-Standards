package pe.edu.upc.models

data class Review(val review_id:Int,
                  val user_id:Int,val name: String, val username:String,val phone_number:String,
                  val foodtruck_id:Int, val content:String, val title: String, val date: String)