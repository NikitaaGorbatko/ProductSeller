package nikitagorbatko.example.productseller

import android.Manifest
import android.app.DownloadManager
import android.content.Context

import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.*
import nikitagorbatko.example.productseller.contacts.FragmentContacts
import nikitagorbatko.example.productseller.orders.FragmentOrders
import nikitagorbatko.example.productseller.products.FragmentProducts


class MainActivity : AppCompatActivity(), DownloadFileManager.DownloadFileListener {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var products: String
    private lateinit var clients: String
    private lateinit var orders: String
    private val fragmentProducts = FragmentProducts()
    private lateinit var fragmentContacts: FragmentContacts
    private val fragmentOrders = FragmentOrders()
    private val REQUEST_RUNTIME_PERMISSION = 123
    private val permissons = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applicationContext.getSystemService(DOWNLOAD_SERVICE)

        products = resources.getString(R.string.products)
        clients = resources.getString(R.string.clients)
        orders = resources.getString(R.string.orders)
        fragmentContacts = FragmentContacts(contentResolver)

        setSupportActionBar(findViewById(R.id.toolbar))
        val result = ContextCompat.checkSelfPermission(
            baseContext,
            Manifest.permission.READ_CONTACTS
        )

        ActivityResultContracts.RequestPermission()

        requestPermissions(permissons, REQUEST_RUNTIME_PERMISSION)


        toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setTitleTextColor(Color.WHITE)
        }
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_products -> setFragment(fragmentProducts, products)
                R.id.action_clients -> setFragment(fragmentContacts, clients)
                R.id.action_orders -> setFragment(fragmentOrders, orders)
            }
            true
        }


        setFragment(FragmentProducts(), products)

        val manager = DownloadFileManager()
        manager.start(File("","hello", ".txt", ""), baseContext)
        manager.addListener(this)
    }

    override fun onNext(bytesDownloaded: Int, fileKey: String, downloadedFileId: Long?) {
        TODO("Not yet implemented")
    }

    override fun onComplete(
        downloadedFileId: Long,
        fileKey: String,
        file: File?,
        error: Throwable?,
        isSuccess: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val contactData: Uri? = data?.data
            val c: Cursor? = contentResolver.query(contactData!!, null, null, null, null)
            if (c!!.moveToFirst()) {
                var phoneNumber = ""
                var emailAddress = ""
                val name: String =
                    c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val contactId: String = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))
                //http://stackoverflow.com/questions/866769/how-to-call-android-contacts-list   our upvoted answer
                var hasPhone: String =
                    c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                hasPhone = if (hasPhone.equals("1", ignoreCase = true)) "true" else "false"
                if (java.lang.Boolean.parseBoolean(hasPhone)) {
                    val phones: Cursor? = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                        null,
                        null
                    )
                    while (phones!!.moveToNext()) {
                        phoneNumber =
                            phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    }
                    phones.close()
                }

                // Find Email Addresses
                val emails: Cursor? = contentResolver.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                    null,
                    null
                )
                while (emails!!.moveToNext()) {
                    emailAddress =
                        emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                }
                emails.close()

                //mainActivity.onBackPressed();
                 Toast.makeText(baseContext, "${phoneNumber.toString()}", Toast.LENGTH_SHORT).show();
//                tvname.setText("Name: $name")
//                tvphone.setText("Phone: $phoneNumber")
//                tvmail.setText("Email: $emailAddress")
//                Log.d("curs", "$name num$phoneNumber mail$emailAddress")
            }
            c.close()
        }
    }



    private fun setFragment(fragment: Fragment, titleText: String) {
        title = titleText
        supportFragmentManager.commit { replace(R.id.main_root, fragment) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}