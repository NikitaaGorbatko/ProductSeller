package nikitagorbatko.example.productseller

import android.annotation.SuppressLint
import android.database.Cursor
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.loader.app.LoaderManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var products: String
    private lateinit var clients: String
    private lateinit var orders: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoaderManager.getInstance(this)

        products = resources.getString(R.string.products)
        clients = resources.getString(R.string.clients)
        orders = resources.getString(R.string.orders)

        setSupportActionBar(findViewById(R.id.toolbar))

        toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setTitleTextColor(Color.WHITE)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_products -> setFragment(FragmentProducts(), products)
                R.id.action_clients -> setFragment(FragmentProducts(), clients)
                R.id.action_orders -> setFragment(FragmentProducts(), orders)
            }
            true
        }

        supportFragmentManager.commit {
            title = products
            replace(R.id.main_root, FragmentProducts())
        }
    }

    private fun setFragment(fragment: Fragment, titleText: String) {
        title = titleText
        supportFragmentManager.commit {
            replace(R.id.main_root, fragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}