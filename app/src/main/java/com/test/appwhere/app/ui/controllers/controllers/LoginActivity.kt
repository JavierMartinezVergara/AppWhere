package com.test.appwhere.app.ui.controllers.controllers

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.OnCompleteListener
import com.icom.baselivedata.utils.Status
import com.icom.terramar.app.ui.controllers.utils.Utils
import com.test.appwhere.R
import com.test.appwhere.app.viewmodels.AppWhereViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_addsucursal.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewmodel: AppWhereViewModel
    var user: String? = null
    var pass: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        txt_edit_userEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txt_edit_userEmail.text.toString() != null || txt_edit_userPass.text.toString() != "") {
                    login.isEnabled = true
                }


            }
        })



        txt_edit_userPass.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txt_edit_userEmail.text.toString() != null || txt_edit_userPass.text.toString() != "") {
                    login.isEnabled = true
                }


            }
        })


        fun subscribeViewModel(){
            viewmodel.loginLiveData.observe(this, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        //animation_view.visibility = View.GONE


                        if (it.data?.data?.successful!!) {

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(Intent(intent)).apply {
}

                        }
                    }
                    Status.LOADING -> {}
                    Status.ERROR -> {
                        //animation_view.visibility = View.GONE

                    }
                }

            })
        }






        login.setOnClickListener {
            user = txt_edit_userEmail.text.toString()
            pass = txt_edit_userPass.text.toString()


            if(!Utils.isOnline(this)){
               // dialogo("No tienes Internet")
            }
            else {
                viewmodel.login(user!!, pass!!)
                subscribeViewModel()
            }



        }



    }


    override fun onResume() {
        super.onResume()
    }



}