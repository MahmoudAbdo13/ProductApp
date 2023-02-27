package com.grand.navigation.ui.productDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.grand.navigation.R
import com.grand.navigation.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

     @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val binding = FragmentDetailsBinding.bind(view)

         binding.apply {
             Glide.with(this@DetailsFragment).load(args.product.images[0]).into(productImage)
             productTitle.text = args.product.title
             productDetails.text = args.product.description
             price.txtHead.text = args.product.price.toString()
             rateNumber.text = args.product.rating.toString()
             stock.text = "(${args.product.stock}+)"
             addToCart.titleBtn.text = this@DetailsFragment.getString(R.string.add_to_cart)
             addToCart.iconImage.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_cart) });
             //addToCart.iconImage.setBackgroundResource(R.drawable.white_circle)
             
             detailsBack.setOnClickListener{
                 val action = DetailsFragmentDirections.actionDetailsFragmentToHomeFragment()
                 binding.root.findNavController().navigate(action)
             }
         }
    }
}