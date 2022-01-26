package com.example.authflow.presentation.number_detail

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.example.authflow.R
import com.example.authflow.databinding.FragmentNumberDetailBinding
import com.google.android.material.transition.MaterialContainerTransform


class NumberDetailFragment : Fragment() {

    private lateinit var numberDetailBinding: FragmentNumberDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         sharedElementEnterTransition = MaterialContainerTransform().apply {
          drawingViewId = R.id.myNavHostFragment
          duration = 300.toLong()
           scrimColor = Color.TRANSPARENT
             //setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
           }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        numberDetailBinding = FragmentNumberDetailBinding.inflate(inflater, container, false)

        val view = numberDetailBinding.root



        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 100
        }

        val position = requireArguments().getInt("pos")
        ViewCompat.setTransitionName(numberDetailBinding.numberDetailTxt, "trans_$position")



        numberDetailBinding.numberDetailTxt.setText(requireArguments().getString("number_detail"))


        return view
    }

}