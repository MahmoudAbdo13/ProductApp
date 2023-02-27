package com.grand.navigation.ui.home.fragments.food

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grand.navigation.R
import com.grand.navigation.databinding.FoodItemBinding
import com.grand.navigation.models.Product
import com.grand.navigation.models.SearchResponse


class ProductAdapter(val fragment:Fragment,val response: SearchResponse) : RecyclerView.Adapter<ProductAdapter.Holder>() {
    var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemSelected(product: Product)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = response.products[position]
        holder.bind(currentItem, fragment, position, response.limit)
        holder.itemView.setOnClickListener {
            mListener?.onItemSelected(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return response.products.size
    }

    class Holder(private val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: Product, fragment:Fragment, position: Int, result: Int) {
            binding.apply {
                if (position == 0) {
                    found30Re.visibility = View.VISIBLE
                    found30Re.text = "Found \n$result results"
                    item.visibility = View.GONE
                } else {
                    found30Re.visibility = View.GONE
                    item.visibility = View.VISIBLE

                    productTitle.text = product.title
                    productBrand.text = product.brand
                    rate.text = product.rating.toString()
                    rateNumber.text = "(${product.stock}+)"
                    foodPrice.txtHead.text = product.price.toString()
                    foodPrice.image.setTextColor(fragment.resources.getColor(R.color.indicator))

                    Glide.with(fragment.requireContext()).load(product.images[0]).into(productImage)

                }
            }
        }
    }
}