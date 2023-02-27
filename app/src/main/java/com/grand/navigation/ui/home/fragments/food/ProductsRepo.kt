package com.grand.navigation.ui.home.fragments.food

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.grand.navigation.models.SearchResponse
import com.grand.navigation.network.ApiService
import kotlinx.coroutines.*


class ProductsRepo(private val api: ApiService) {
    private var response = ArrayList<SearchResponse>()
    lateinit var notifyChange: NotifyDataChanged
    fun initializeModel(mFragment: Fragment) {
        notifyChange = mFragment as NotifyDataChanged
    }

    private fun getSearch(search:String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.search(search);
            withContext(Dispatchers.Main)
            {
                this@ProductsRepo.response.add(response)
                Log.e("searchresult", response.total.toString())
                notifyChange.searchResult()
            }
        }
    }

    fun searchResult(search:String): MutableLiveData<ArrayList<SearchResponse>> {
        response.clear()
        getSearch(search)
        for(item in response)
        {
            Log.e("searchresult", item.products.get(0).title)
        }
        val mMutable = MutableLiveData<ArrayList<SearchResponse>>()
        mMutable.postValue(response)
        return mMutable
    }
    private fun getProducts() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getProducts();
            withContext(Dispatchers.Main)
            {
                this@ProductsRepo.response.add(response)
                notifyChange.dataChanged()
            }
        }
    }

    fun liveData(): MutableLiveData<ArrayList<SearchResponse>> {
        response.clear()
        getProducts()
        val mMutable = MutableLiveData<ArrayList<SearchResponse>>()
        mMutable.postValue(response)
        return mMutable
    }
}

