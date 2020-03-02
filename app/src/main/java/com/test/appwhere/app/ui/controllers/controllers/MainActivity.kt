package com.test.appwhere.app.ui.controllers.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.icom.baselivedata.utils.Status
import com.test.appwhere.R
import com.test.appwhere.app.ui.controllers.fragments.AddFragment
import com.test.appwhere.app.ui.controllers.fragments.MapsFragment
import com.test.appwhere.app.ui.controllers.fragments.RecyclerFragment
import com.test.appwhere.app.ui.controllers.models.MerchantsMarkers
import com.test.appwhere.app.viewmodels.AppWhereViewModel
import com.test.appwhere.domain.models.MerchantsDomain
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    var rec : MutableList<MerchantsMarkers>? = ArrayList()

    @Inject
    lateinit var viewmodel : AppWhereViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            viewmodel.getMerchants()
            viewmodel.getMerchantsLiveData.observe(this, Observer {

                when(it.status){

                    Status.SUCCESS-> {
                        //animation_view_videos.visibility = View.GONE
                        for(markers in it.data?.data?.merchants?.indices!!){
                            rec?.add(markers, MerchantsMarkers(it.data?.data?.merchants!![markers].merchantName,
                                it.data?.data?.merchants!![markers].latitude,
                                it.data?.data?.merchants!![markers].longitude))

                        }
                        loadFragment(MapsFragment.newInstance((rec as ArrayList<MerchantsMarkers>?)!!))

                    }
                    Status.LOADING -> {
                        // animation_view_videos.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        //animation_view_videos.visibility = View.GONE
                    }

                }
            })


        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    val fragment = MapsFragment.newInstance((rec as ArrayList<MerchantsMarkers>?)!!)
                    loadFragment(fragment)
                    true
                }
                R.id.navigation_sucursales -> {
                    val fragment = RecyclerFragment.newInstance()
                    loadFragment(fragment)
                    true
                }
                R.id.navigation_add -> {
                    val fragment = AddFragment.newInstance()
                    loadFragment(fragment)
                    true
                }
                else -> false
            }
        }



        //bottom_navigation.selectedItemId = R.id.navigation_home
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}
