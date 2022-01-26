package com.example.authflow.presentation.nested_recycler.child_view_recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.authflow.databinding.ProductMainItemBinding
import com.example.authflow.databinding.ProductSecondItemBinding
import com.example.authflow.databinding.ProductTerciaryItemBinding

sealed class ChildAdapterViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    class BigTextHolder(val productMainItemBinding: ProductMainItemBinding): ChildAdapterViewHolder(productMainItemBinding){
        fun bind(name: String){
            productMainItemBinding.mainItemTxt.setText(name)
        }
    }

    class MediumTextHolder(val secondItemBinding: ProductSecondItemBinding): ChildAdapterViewHolder(secondItemBinding){
        fun bind(name: String){
            secondItemBinding.secondItemTxt.setText(name)
        }
    }

    class RoundedTextHolder(val terciaryItemBinding: ProductTerciaryItemBinding): ChildAdapterViewHolder(terciaryItemBinding){
        fun bind(name: String){
            terciaryItemBinding.terciaryItemTxt.setText(name)
        }
    }
}