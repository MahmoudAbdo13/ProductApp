package com.grand.navigation.ui.home.fragments.food

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grand.navigation.models.SearchResponse

class ProductViewModel(private val repo: ProductsRepo) : ViewModel() {

    private var mData = MutableLiveData<ArrayList<SearchResponse>>()
    fun intialViewModel(mFragment: Fragment) {
        repo.initializeModel(mFragment)
        mData = repo.liveData()
        Log.e("intialViewModel", "fired")
    }

    fun getProducts(): MutableLiveData<ArrayList<SearchResponse>> {
        return mData
    }
    fun setSearch(search:String)
    {
        mData=repo.searchResult(search)
    }
    fun search(): MutableLiveData<ArrayList<SearchResponse>> {
        return mData
    }
}