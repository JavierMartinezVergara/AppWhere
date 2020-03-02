package com.test.appwhere.app.ui.controllers.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.icom.baselivedata.utils.Status
import com.test.appwhere.R
import com.test.appwhere.app.ui.controllers.adapters.MerchantsAdapter
import com.test.appwhere.app.viewmodels.AppWhereViewModel
import com.test.appwhere.domain.models.MerchantsDomain
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_sucursales.*
import javax.inject.Inject


class RecyclerFragment: DaggerFragment(){



    @Inject
    lateinit var viewmodel : AppWhereViewModel
    var mAdapter : MerchantsAdapter? = null
    var rec : List<MerchantsDomain>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sucursales, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getMerchants()
        viewmodel.getMerchantsLiveData.observe(this, Observer {

            when(it.status){

                Status.SUCCESS-> {
                    //animation_view_videos.visibility = View.GONE
                        if(it.data?.data?.merchants?.size!! > 0){
                            rec = it.data?.data!!.merchants
                            setUpRecyclerView(rec, context!!)

                        }



                }
                Status.LOADING -> {
                   // animation_view_videos.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //animation_view_videos.visibility = View.GONE
                }

            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun setUpRecyclerView(list : List<MerchantsDomain>?, context : Context){

        recycler_sucursal.setHasFixedSize(true)
        recycler_sucursal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter  = MerchantsAdapter(list, context)
        recycler_sucursal.adapter = mAdapter
    }





    companion object {
        @JvmStatic
        fun newInstance() =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}
