package pe.edu.upc.models

class Foodtruck(val name: String, val foodType: String, val avgCost: Double)

object DataService{
    val foodtrucks = listOf(
        Foodtruck("FT1","Carne",10.0),
        Foodtruck("FT2","Veggie",20.0),
        Foodtruck("FT3","Makki",30.0)
    )
}