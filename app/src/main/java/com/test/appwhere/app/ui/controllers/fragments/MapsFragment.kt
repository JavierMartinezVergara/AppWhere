package com.test.appwhere.app.ui.controllers.fragments

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabLayout
import com.icom.baselivedata.utils.Status
import com.test.appwhere.R
import com.test.appwhere.app.ui.controllers.models.MerchantsMarkers
import com.test.appwhere.app.viewmodels.AppWhereViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject



private const val ARRAY = "array"
class MapsFragment: DaggerFragment(), OnMapReadyCallback {
    var arrayMarkers : ArrayList<MerchantsMarkers>? = null
    var latLng : LatLng? = null

    @Inject
    lateinit var viewmodel : AppWhereViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
arrayMarkers = it.getParcelableArrayList(ARRAY)
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getMerchants()
        viewmodel.getMerchantsLiveData.observe(this, Observer {

            when(it.status){

                Status.SUCCESS-> {
                    //animation_view_videos.visibility = View.GONE
                    for (tabNum in it.data?.data?.merchants!!.indices) {
                        if(it.data?.data?.merchants?.size!! > 0){
                            arrayMarkers?.add(tabNum, MerchantsMarkers(it.data?.data!!.merchants[tabNum].merchantName,
                                it.data?.data!!.merchants[tabNum].latitude, it.data?.data!!.merchants[tabNum].longitude ))
                        }
                    }

                }
                Status.LOADING -> {
                //    animation_view_videos.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                //    animation_view_videos.visibility = View.GONE
                }

            }
        })

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(arrayMarkers : ArrayList<MerchantsMarkers>) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARRAY, arrayMarkers)
                }
            }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        var mMap = googleMap

        // Add a marker in Sydney and move the camera

        for (markers in arrayMarkers!!.indices) {
            if(arrayMarkers!!.size > 0){
                var marker = LatLng(
                    arrayMarkers!![markers].latitude,
                    arrayMarkers!![markers].longitude
                )
                mMap?.addMarker(
                    MarkerOptions().position(marker).title(arrayMarkers!![markers].merchantName).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.pin_ubicacion)))
                latLng = LatLng(arrayMarkers!![markers].latitude,
                    arrayMarkers!![markers].longitude)
            }
        }

        mMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }


}
