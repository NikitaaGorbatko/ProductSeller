package nikitagorbatko.example.productseller.contacts

import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import nikitagorbatko.example.productseller.Contact
import nikitagorbatko.example.productseller.R


class FragmentContacts(resolver: ContentResolver): Fragment() {
    private val contentResolver = resolver
    private val contacts = ArrayList<Contact>()
    lateinit var contactsList: ListView
    private lateinit var baseInflater: LayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)
        contactsList = view.findViewById(R.id.contacts_list_view)
        baseInflater = inflater
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
            contacts.add(Contact(name, phoneNumber))
        }
        phones.close()

        contactsList.adapter = ArrayAdapter(requireContext(), R.layout.fragment_contacts, contacts)
        return view
    }

    private class MyAdapter(layoutInflater: LayoutInflater) : BaseAdapter() {
        private val inflater = layoutInflater
        // override other abstract methods here
        override fun getView(position: Int, convertView: View, container: ViewGroup): View {
//            var convertView: View? = convertView
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.list_item, container, false)
//            }
//            (convertView.findViewById<View>(android.R.id.text1) as TextView)
//                .setText(getItem(position))
//            return convertView
            return convertView
        }

        override fun getCount(): Int {
            TODO("Not yet implemented")
        }

        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long {
            TODO("Not yet implemented")
        }
    }

}