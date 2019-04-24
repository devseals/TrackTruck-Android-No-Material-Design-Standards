package pe.edu.upc.services

object DataServiceU {
    var id= 0
    var name= ""
    var authToken :String =""
    var isLogged : Boolean = false

    fun logout(){
         id = 0
         name =""
         authToken=""
         isLogged = false
    }
}

object DataServiceO {
    var id= 0
    var name= ""
    var authToken :String =""
    var isLogged : Boolean = false

    fun logout(){
         id = 0
         name =""
         authToken  =""
         isLogged = false
    }
}