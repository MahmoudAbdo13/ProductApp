package com.grand.navigation.ui.home.fragments.food

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.grand.navigation.R
import com.grand.navigation.databinding.FragmentFoodBinding
import com.grand.navigation.ui.home.HomeFragmentDirections
import com.grand.navigation.models.Product
import com.grand.navigation.models.SearchResponse
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FoodFragment : Fragment(R.layout.fragment_food), NotifyDataChanged {
    private lateinit var binding: FragmentFoodBinding
    private val productViewModel: ProductViewModel by sharedViewModel()
    private lateinit var products: SearchResponse
    private val adapter: ProductAdapter by lazy {
        ProductAdapter(this, products)
    }
    private lateinit var viewModel: MyObseravble

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFoodBinding.bind(view)
        productViewModel.intialViewModel(this)
        binding.apply {
            foodRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                setHasFixedSize(true)
            }
        }
        viewModel = activity?.run {
            ViewModelProvider(this).get(MyObseravble::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel.data.observe(viewLifecycleOwner, Observer {
            var search= viewModel.data.value
            if (search != null) {
                productViewModel.setSearch(search)
                Log.e("search4", search)
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun dataChanged() {
        productViewModel.getProducts().observe(viewLifecycleOwner) {
            Log.e("this", "dataChanged")
            if (it.isNotEmpty()) {
                binding.foodRecyclerView.visibility= View.VISIBLE
                binding.noFoods.visibility= View.INVISIBLE
                products = it[0]
                binding.foodRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.INVISIBLE
                onClickItemAdapter()
            }
            else
            {
                binding.foodRecyclerView.visibility= View.INVISIBLE
                binding.noFoods.visibility= View.VISIBLE

            }
        }
    }

    override fun searchResult() {
        productViewModel.search().observe(viewLifecycleOwner) {
            Log.e("this", "dataChanged")
         if (it.isNotEmpty()) {
             binding.foodRecyclerView.visibility= View.VISIBLE
             binding.noFoods.visibility= View.INVISIBLE
             products = it[0]
             binding.foodRecyclerView.adapter = adapter
             adapter.notifyDataSetChanged()
             binding.progressBar.visibility = View.INVISIBLE
         }
         else
         {
             binding.foodRecyclerView.visibility= View.INVISIBLE
             binding.noFoods.visibility= View.VISIBLE

         }

        }
    }
    private fun onClickItemAdapter() {
        adapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener {
            override fun onItemSelected(product: Product) {
                val action =
                    HomeFragmentDirections.actionFoodFragmentToDetailsFragment(
                        product
                    )
                binding.root.findNavController().navigate(action)
            }
        })
    }
}