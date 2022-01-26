package com.example.authflow.presentation.nested_recycler.child_view_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.authflow.R
import com.example.authflow.databinding.ProductMainItemBinding
import com.example.authflow.databinding.ProductSecondItemBinding
import com.example.authflow.databinding.ProductTerciaryItemBinding
import java.lang.IllegalArgumentException

class ChildRecyclerAdapter(private val type: Int) :
    RecyclerView.Adapter<ChildAdapterViewHolder>()
    //PagingDataAdapter<String, ChildAdapterViewHolder>(PRODUCT_COMPARATOR)

{

    var allItems: List<String> = emptyList()
    internal lateinit var onItemClickedListener: OnItemClickedListener

    override fun onBindViewHolder(holder: ChildAdapterViewHolder, position: Int) {
        val currentItem = allItems.get(position)

        if (currentItem != null) {
              when(holder){
                is ChildAdapterViewHolder.BigTextHolder -> holder.bind(currentItem)
                is ChildAdapterViewHolder.MediumTextHolder -> holder.bind(currentItem)
                is ChildAdapterViewHolder.RoundedTextHolder -> holder.bind(currentItem)
            }
        }
        ViewCompat.setTransitionName(holder.itemView, "trans_$position")
        holder.itemView.setOnClickListener {
            onItemClickedListener.onElementClicked(holder.itemView, currentItem, position)
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildAdapterViewHolder {
        return when(type){
            0 -> ChildAdapterViewHolder.BigTextHolder (
                ProductMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            1 -> ChildAdapterViewHolder.MediumTextHolder (
                ProductSecondItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            2 ->  ChildAdapterViewHolder.RoundedTextHolder (
                ProductTerciaryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            else -> throw IllegalArgumentException("blabla bla")
        }
    }

    fun setData(items: List<String>, onItemClickedListener: OnItemClickedListener){
        allItems = items
        notifyDataSetChanged()
        this.onItemClickedListener = onItemClickedListener
    }

    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem
        }
    }

    override fun getItemCount() = allItems.size




    interface OnItemClickedListener{
        fun onElementClicked(view: View, element: String, position: Int)
    }
}