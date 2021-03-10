package nikitagorbatko.example.productseller.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nikitagorbatko.example.productseller.Product
import nikitagorbatko.example.productseller.R
import nikitagorbatko.example.productseller.Type

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val products = ArrayList<Product>()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textName)
        val cost: TextView = view.findViewById(R.id.textCost)
        val volume: TextView = view.findViewById(R.id.textVolume)
    }

    fun addProducts(productList: MutableList<Product>) {
        products.addAll(productList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = products[position].name
        holder.cost.text = products[position].cost.toString().plus("₽")
        holder.volume.text = when(product.type) {
            Type.GRAMS -> product.cost.toString() + "гр"
            Type.MILLILITERS -> product.cost.toString() + "мл"
            Type.COUNT -> "1шт"
        }
    }

    override fun getItemCount(): Int = products.size
}