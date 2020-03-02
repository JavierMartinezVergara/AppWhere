package com.test.appwhere.app.ui.controllers.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.icom.baselivedata.utils.Status
import com.test.appwhere.R
import com.test.appwhere.app.viewmodels.AppWhereViewModel
import com.test.appwhere.data.models.request.RegisterMerchantRequest
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_addsucursal.*
import javax.inject.Inject


class AddFragment: DaggerFragment(){



    @Inject
    lateinit var viewmodel : AppWhereViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addsucursal, container, false)
        txt_edit_userName.setText("")
        txt_edit_userAddress.setText("")
        txt_edit_userTel.setText("")
        txt_edit_Latitud.setText("")
        txt_edit_Longitud.setText("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        txt_edit_userName.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(txt_edit_userName.text.toString() != "" && txt_edit_userAddress.text.toString() != "" && txt_edit_userTel.text.toString() != "" && txt_edit_Latitud.text.toString() != "" && txt_edit_Longitud.text.toString() != ""){
                    add.isEnabled = true
                } else {
                    add.isEnabled = false
                }
            }
        })

        txt_edit_userAddress.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(txt_edit_userName.text.toString() != "" && txt_edit_userAddress.text.toString() != "" && txt_edit_userTel.text.toString() != "" && txt_edit_Latitud.text.toString() != "" && txt_edit_Longitud.text.toString() != ""){
                    add.isEnabled = true
                } else {
                    add.isEnabled = false
                }
            }
        })

        txt_edit_Latitud.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(txt_edit_userName.text.toString() != "" && txt_edit_userAddress.text.toString() != "" && txt_edit_userTel.text.toString() != "" && txt_edit_Latitud.text.toString() != "" && txt_edit_Longitud.text.toString() != ""){
                    add.isEnabled = true
                } else {
                    add.isEnabled = false
                }
            }
        })

        txt_edit_userTel.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(txt_edit_userName.text.toString() != "" && txt_edit_userAddress.text.toString() != "" && txt_edit_userTel.text.toString() != "" && txt_edit_Latitud.text.toString() != "" && txt_edit_Longitud.text.toString() != ""){
                    add.isEnabled = true
                } else {
                    add.isEnabled = false
                }
            }
        })

        txt_edit_Longitud.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(txt_edit_userName.text.toString() != "" && txt_edit_userAddress.text.toString() != "" && txt_edit_userTel.text.toString() != "" && txt_edit_Latitud.text.toString() != "" && txt_edit_Longitud.text.toString() != ""){
                    add.isEnabled = true
                } else {
                    add.isEnabled = false
                }
            }
        })

        add.setOnClickListener {
            var name = txt_edit_userName.text.toString()
            var direccion = txt_edit_userAddress.text.toString()
            var telefono = txt_edit_userTel.text.toString()
            var latitude = txt_edit_Latitud.text.toString()
            var longitud = txt_edit_Longitud.text.toString()

            var registerMerchantRequest = RegisterMerchantRequest(latitude.toDouble(),longitud.toDouble(), direccion, name, telefono)
            viewmodel.requestMerchants(registerMerchantRequest)
            viewmodel.requestMerchantsLiveData.observe(this, Observer {

                when(it.status){

                    Status.SUCCESS-> {
                        //animation_view_videos.visibility = View.GONE
                        if(it.data?.data?.status == 1){
                            it.data?.data?.description

                        }

                    }
                    Status.LOADING -> {
                        //animation_view_videos.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        //animation_view_videos.visibility = View.GONE
                    }

                }
            })
        }




    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }





    companion object {
        @JvmStatic
        fun newInstance() =
            AddFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}
