package com.example.authflow.presentation.nested_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authflow.databinding.CategoryItemBinding
import com.example.authflow.domain.model.Products
import com.example.authflow.presentation.nested_recycler.child_view_recycler.ChildRecyclerAdapter
import kotlinx.coroutines.runBlocking

class NestedRecyclerAdapter(val onItemClickedListener: ChildRecyclerAdapter.OnItemClickedListener): PagingDataAdapter<Products, NestedRecyclerAdapter.ViewHolder>(PRODUCT_COMPARATOR){




    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem, position, onItemClickedListener )
        }

        //this.snapshot()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    fun setChildClickListener(){


    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder (private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(item: Products, position: Int,onItemClickedListener: ChildRecyclerAdapter.OnItemClickedListener){
                binding.categoryName.setText(item.category)
                val childAdapter = ChildRecyclerAdapter(position.mod(3))
                childAdapter.setData(item.productList, onItemClickedListener)
                runBlocking {
                    //childAdapter.submitData(item.productList as PagingData<String>)
                }

                binding.categoryItem.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL,false)
                binding.categoryItem.adapter = childAdapter
               // itemView.child_recycler_view.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)
               //  itemView.child_recycler_view.adapter = childMembersAdapter

            }

    }

    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<Products>() {
            override fun areItemsTheSame(oldItem: Products, newItem: Products) =
                oldItem.category == newItem.category

            override fun areContentsTheSame(oldItem: Products, newItem: Products) =
                oldItem == newItem
           // Log.d("","")
        }


    }


}