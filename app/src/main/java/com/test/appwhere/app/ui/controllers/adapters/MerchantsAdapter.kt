package com.test.appwhere.app.ui.controllers.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.test.appwhere.R
import com.test.appwhere.domain.models.MerchantsDomain
import kotlinx.android.synthetic.main.item_contacto.view.*

class MerchantsAdapter(val items : List<MerchantsDomain>?, val context: Context) : RecyclerView.Adapter<MerchantsAdapter.ViewHolder1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        return ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {

        holder.nombre.text = items?.get(position)?.merchantName
        holder.direccion.text = items?.get(position)?.merchantAddress
        holder.tel.text = items?.get(position)?.merchantTelephone

        }

    override fun getItemCount(): Int {
        return items!!.size
    }

    inner class ViewHolder1 (view: View) : RecyclerView.ViewHolder(view){
        val nombre = view.name
        val direccion = view.direccion
        val tel = view.telfono

    }




}






