package nikitagorbatko.example.productseller

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class ContactsRepository: LoaderManager.LoaderCallbacks<Cursor> {

    init {

    }
    private fun retrieveContacts() {
        @SuppressLint("InlinedApi")
        val PROJECTION: Array<out String> = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            else
                ContactsContract.Contacts.DISPLAY_NAME)
        PROJECTION.size

        @SuppressLint("InlinedApi")
        val FROM_COLUMNS: Array<String> = arrayOf(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
        FROM_COLUMNS.size
        //Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        // OPTIONAL: Makes search string into pattern
//        searchString = "%$mSearchString%"
//
//        searchString?.also {
//            // Puts the search string into the selection criteria
//            selectionArgs[0] = it
//        }
//        // Starts the query
//        return activity?.let {
//            CursorLoader(
//                it,
//                ContactsContract.Data.CONTENT_URI,
//                PROJECTION,
//                SELECTION,
//                selectionArgs,
//                null
//            )
//        } ?: throw IllegalStateException()
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        TODO("Not yet implemented")
    }
}