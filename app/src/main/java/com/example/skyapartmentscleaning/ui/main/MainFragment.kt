package com.example.skyapartmentscleaning.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.skyapartmentscleaning.R
import com.example.skyapartmentscleaning.ui.allApart.AllApartmentsFragment
import com.example.skyapartmentscleaning.ui.adapter.ApartsListAdapter
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var listAdapter: ApartsListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.main_fragment, null)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        floatingActionButton.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, AllApartmentsFragment.newInstance())
                .addToBackStack("AllApart")
                .commit()
        }

        listAdapter = ApartsListAdapter{}
        intitRV()
        viewModel.verifiedApartments.observe(viewLifecycleOwner,{
            it?.let {
                listAdapter.listAparts = it.listApart
            }
        })

    }

    private fun intitRV() {
        apart_history_list_rv.setHasFixedSize(true)
        apart_history_list_rv.layoutManager = GridLayoutManager(context, 3)
        apart_history_list_rv.adapter = listAdapter
    }

}