package pe.edu.upc.models

data class Foodtruck
    (val id: Int, val latitude: Double, val longitude:Double, val name: String, val food_type: String, val owner_id:Int, val avg_price:Double)