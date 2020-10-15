package com.mughitszufar.whatsappclown.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mughitszufar.whatsappclown.R
import com.mughitszufar.whatsappclown.adapter.ContactsAdapter
import com.mughitszufar.whatsappclown.listener.ContactClickListener
import com.mughitszufar.whatsappclown.util.Contact
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity(), ContactClickListener {

    private val contactList = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        setUpList()
        getContact()
    }

    private fun getContact() {
        progress_layout_contact.visibility= View.VISIBLE
        contactList.clear()
        val newList = ArrayList<Contact>()
        val phone = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
        )

        while (phone!!.moveToNext()){
            val name = phone.getString(
                phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            )
            val phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            newList.add(Contact(name, phoneNumber))
        }
        progress_layout_contact.visibility = View.GONE

        contactList.addAll(newList)
        phone.close()
    }

    private fun setUpList() {
        progress_layout_contact.visibility= View.GONE
        val contactsAdapter = ContactsAdapter(contactList)
        contactsAdapter.setOnItemClickListener(this)
        rv_contacts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = contactsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        }


    }

    override fun onContactClickListener(name: String?, phone: String?) {
        val intent = intent
        intent.putExtra(MainActivity.PARAM_NAME, name)
        intent.putExtra(MainActivity.PARAM_PHONE, phone)
        setResult(Activity.RESULT_OK, intent)
        finish()

    }
}