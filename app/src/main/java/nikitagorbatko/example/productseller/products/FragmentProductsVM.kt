package nikitagorbatko.example.productseller.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nikitagorbatko.example.productseller.Product
import nikitagorbatko.example.productseller.Type

class FragmentProductsVM: ViewModel() {
    private val productsLiveData = MutableLiveData<MutableList<Product>>()
    private val products = mutableListOf(
        Product("Цельное молоко", 110, 1500, Type.MILLILITERS),
        Product("Сливки", 240, 450, Type.MILLILITERS),
        Product("Творог обезжиренный", 240, 1000, Type.GRAMS),
        Product("Масло сливочное", 660, 1000, Type.GRAMS),
        Product("Масло бутербродное", 530, 1000, Type.GRAMS),
        Product("Гхи", 470, 400, Type.GRAMS),
        Product("Адыгейский сыр", 400, 1000, Type.GRAMS),

        Product("Сулугуни большой", 350, 550, Type.GRAMS),
        Product("Сулугуни маленький", 230, 310, Type.GRAMS),
        Product("Сулугуни копченый", 250, 310, Type.GRAMS),
        Product("Сулугуни копченый со специями", 260, 310, Type.GRAMS),
        Product("Панир маленький", 230, 390, Type.GRAMS),
        Product("Панир копченый", 250, 390, Type.GRAMS),
        Product("Косичка белая малосольная", 180, type = Type.COUNT),
        Product("Косичка копченая малосольная", 200, type = Type.COUNT),
        Product("Твёрдый зрелый сыр с орехом", 980, 1000, Type.GRAMS),
        Product("Мёд майский(липа, груша, каштан)", 2000, 3000, Type.MILLILITERS),
        Product("Кунжутная халва", 200, 380, Type.GRAMS),
        Product("Кунжутная паста тахини", 280, 500, Type.GRAMS),
        Product("Иван-чай", 300, 250, Type.GRAMS),
        Product("Цветки иван-чая", 150, 100, Type.GRAMS),
        Product("Таволга", 150, 100, Type.GRAMS)

    )

    init {
        productsLiveData.postValue(products)
    }
    //
    //
    //
    //

    fun getProducts() = productsLiveData
}