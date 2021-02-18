package nikitagorbatko.example.productseller.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nikitagorbatko.example.productseller.Product

class FragmentProductsVM: ViewModel() {
    private val productsLiveData = MutableLiveData<MutableList<Product>>()
    private val products = mutableListOf<Product>(
        Product("Цельное молоко", 110, 1500, true),
        Product("Сливки", 240, 450, true),
        Product("Творог обезжиренный", 240, 1000, false),
        Product("Масло сливочное", 660, 1000, false),
        Product("Масло бутербродное", 530, 1000, false),
        Product("Гхи", 470, 400, false),
        Product("Адыгейский сыр", 400, 1000, false),

        Product("Сулугуни большой", 350, 550, false),
        Product("Сулугуни маленький", 230, 310, false),
        Product("Сулугуни копченый", 250, 310, false),
        Product("Сулугуни копченый со специями", 260, 310, false),
        Product("Панир маленький", 230, 390, false),
        Product("Панир копченый", 250, 390, false),
        Product("Косичка белая малосольная", 180),
        Product("Косичка копченая малосольная", 200),
        Product("Твёрдый зрелый сыр с орехом", 980, 1000, false),
        Product("Мёд майский(липа, груша, каштан)", 2000, 3000, true),
        Product("Кунжутная халва", 200, 380, false),
        Product("Кунжутная паста тахини", 280, 500, false),
        Product("Иван-чай", 300, 250, false),
        Product("Цветки иван-чая", 150, 100, false),
        Product("Таволга", 150, 100, false)
    )

    init {
        productsLiveData.postValue(products)
    }

    fun getProducts() = productsLiveData
}