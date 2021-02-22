package nikitagorbatko.example.productseller.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nikitagorbatko.example.productseller.R

class FragmentProducts: Fragment() {
    private lateinit var viewModel: FragmentProductsVM
    private val productAdapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)
            .get(FragmentProductsVM::class.java)
        observe()
        context.let {
            viewModel.setContext(it!!)
        }


        val view = inflater.inflate(R.layout.fragment_products, container, false)
        view?.findViewById<RecyclerView>(R.id.recycler_products)?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = productAdapter
        }

        return view
    }
    fun observe() {
        viewModel.getProducts().observe(viewLifecycleOwner, Observer {
            productAdapter.addProducts(it)
        })
    }
}