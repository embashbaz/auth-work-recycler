package com.example.authflow.presentation.nested_recycler

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authflow.collectLatestLifecycleFlow
import com.example.authflow.databinding.FragmentNestedRecyclerBinding
import com.example.authflow.presentation.nested_recycler.child_view_recycler.ChildRecyclerAdapter
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint

import androidx.core.view.ViewCompat

import androidx.navigation.fragment.FragmentNavigator
import com.example.authflow.R


@AndroidEntryPoint
class NestedRecyclerFragment : Fragment(), ChildRecyclerAdapter.OnItemClickedListener {

    private lateinit var nestedRecyclerBinding: FragmentNestedRecyclerBinding

    private val nestedRecyclerViewModel: NestedRecyclerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       nestedRecyclerBinding = FragmentNestedRecyclerBinding.inflate(inflater, container, false)
        val view = nestedRecyclerBinding.root

        collectProduct()

        return view
    }

    fun collectProduct(){
        collectLatestLifecycleFlow(nestedRecyclerViewModel.getProducts()){

            val nestedRecyclerAdapter = NestedRecyclerAdapter(this)
            nestedRecyclerAdapter.submitData(viewLifecycleOwner.lifecycle,it)
            Log.d("PAGED DATA FRAGMENT: ", it.toString())



            nestedRecyclerBinding.nestedRecyclerRecycler.layoutManager = LinearLayoutManager(activity)
            nestedRecyclerBinding.nestedRecyclerRecycler.adapter = nestedRecyclerAdapter


        }
    }

    override fun onElementClicked(view: View, name: String, position: Int) {
            val bundle = Bundle()
            bundle.putString("number_detail", name)
            bundle.putInt("pos", position)



       // var pair: Pair<View, String> = Pair.create(holder.itemView, "number_detail_txt")


       // val emailCardDetailTransitionName = getString(com.example.authflow.R.string.email_card_detail_transition_name)
        //val extras = FragmentNavigatorExtras(view.itemView to emailCardDetailTransitionName)

        //MainFragmentDirections.actionMainFragmentToSecondFragment(text, position)
        val extra= FragmentNavigator.Extras.Builder()
            .addSharedElement(view, ViewCompat.getTransitionName(view)!!)
            .build()

        this.findNavController().navigate(R.id.action_nestedRecyclerFragment_to_numberDetailFragment, bundle, null, extra)

    }

}