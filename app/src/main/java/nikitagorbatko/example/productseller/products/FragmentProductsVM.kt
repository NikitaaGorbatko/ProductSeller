package nikitagorbatko.example.productseller.products

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nikitagorbatko.example.productseller.DownloadFileManager
import nikitagorbatko.example.productseller.File
import nikitagorbatko.example.productseller.Product

class FragmentProductsVM: ViewModel(), DownloadFileManager.DownloadFileListener {
    private lateinit var context: Context
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

    fun setContext(context: Context) {this.context = context
    run()
    }

    fun run() {
        viewModelScope.launch {
            l()
        }
    }

    suspend fun l() = withContext(Dispatchers.IO) {
        val manager = DownloadFileManager()
        manager.addListener(this@FragmentProductsVM)
        manager.start(File("https://www.clickdimensions.com/links/TestPDFfile.pdf","TestPDFfile.pdf", ".pdf", ""), context)
    }

    override fun onNext(bytesDownloaded: Int, fileKey: String, downloadedFileId: Long?) {
        val a = 1
    }

    override fun onComplete(
        downloadedFileId: Long,
        fileKey: String,
        file: File?,
        error: Throwable?,
        isSuccess: Boolean
    ) {
        val f = 1
    }

    fun getProducts() = productsLiveData
}