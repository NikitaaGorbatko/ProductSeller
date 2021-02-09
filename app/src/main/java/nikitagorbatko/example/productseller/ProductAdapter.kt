package nikitagorbatko.example.productseller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val products: MutableList<Product>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView
        val cost: TextView
        val volume: TextView

        init {
            name = view.findViewById(R.id.textName)
            cost = view.findViewById(R.id.textCost)
            volume = view.findViewById(R.id.textVolume)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val measure = if (products[position].type) "мл" else "гр"
        //if (products[position].weight.)
        holder.name.text = products[position].name
        holder.cost.text = products[position].cost.toString().plus("₽")
        holder.volume.text = products[position].weight.toString().plus(measure)
    }

    override fun getItemCount(): Int = products.size
}