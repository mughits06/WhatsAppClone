package com.mughitszufar.whatsappclown.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mughitszufar.whatsappclown.R
import com.mughitszufar.whatsappclown.listener.ContactClickListener
import com.mughitszufar.whatsappclown.util.Contact
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_contact.*


class ContactsAdapter(val contact: ArrayList<Contact>):
      RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(){

    private var clickListener: ContactClickListener? = null

    class ContactsViewHolder (override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(contact: Contact, listener : ContactClickListener?){
            txt_contact_name.text = contact.name
            txt_contact_phone.text = contact.phone
            itemView.setOnClickListener {
                listener?.onContactClickListener(contact.name, contact.phone)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false))


    override fun getItemCount() = contact.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bindItem(contact[position], clickListener)

    }

    fun setOnItemClickListener(listener: ContactClickListener){
        clickListener = listener
        notifyDataSetChanged()
    }
}