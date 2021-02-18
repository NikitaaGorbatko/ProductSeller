package nikitagorbatko.example.productseller

//type means ml or gramms if true then ml
data class Product(val name: String, val cost: Int, val weight: Int? = null, val type: Boolean = false)

data class Contact(val name: String, val phone: String)

