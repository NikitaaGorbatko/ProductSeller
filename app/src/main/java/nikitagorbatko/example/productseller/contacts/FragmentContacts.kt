package nikitagorbatko.example.productseller.contacts

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import nikitagorbatko.example.productseller.R

class FragmentContacts(resolver: ContentResolver): Fragment() {
//, LoaderManager.LoaderCallbacks<Cursor>
    private val contentResolver = resolver
    private val searchString: String = ""
    private val selectionArgs = arrayOf(searchString)
    private val TO_IDS: IntArray = intArrayOf(android.R.id.text1)

    @SuppressLint("InlinedApi")
    val PROJECTION: Array<out String> = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.LOOKUP_KEY,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    )

    @SuppressLint("InlinedApi")
    private val SELECTION: String = "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE ?"

    @SuppressLint("InlinedApi")
    val FROM_COLUMNS: Array<String> = arrayOf(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

    private lateinit var cursorAdapter: SimpleCursorAdapter
    lateinit var contactsList: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        contactsList = view.findViewById(R.id.contacts_list_view)
        //LoaderManager.getInstance(this)
        //loaderManager.initLoader(0, null, this)

//        cursorAdapter = SimpleCursorAdapter(
//            context,
//            R.layout.contacts_list_item,
//            null,
//            FROM_COLUMNS, TO_IDS,
//            0
//        )
//        contactsList.adapter = cursorAdapter

        val phones = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )
        while (phones!!.moveToNext()) {
            val name =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
        }
        phones.close()
        return view
    }

//    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
//        selectionArgs[0] = "\$mSearchString%"
//
//        return CursorLoader(
//            requireContext(),
//            ContactsContract.Contacts.CONTENT_URI,
//            PROJECTION,
//            SELECTION,
//            selectionArgs,
//            null
//        )
//    }
//
//    override fun onLoaderReset(loader: Loader<Cursor>) {
//        cursorAdapter.swapCursor(null)
//    }
//
//    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
//        cursorAdapter.swapCursor(data)
//        data.toString()
//    }

}