package com.cjeongmin.assignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cjeongmin.assignment.databinding.ProductItemBinding

class ProductListAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: ProductItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(product: Product) {
            itemBinding.ivImage.setImageResource(product.image)
            itemBinding.tvName.text = product.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}