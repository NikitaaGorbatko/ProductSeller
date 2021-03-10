package nikitagorbatko.example.productseller

data class Product(val name: String, val cost: Int, val weight: Int? = null, val type: Type)

data class Contact(val name: String, val phone: String)

enum class Type {GRAMS, MILLILITERS, COUNT}

