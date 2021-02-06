package com.srjlove.myapplication.view

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.srjlove.myapplication.model.ProductDetails
import com.srjlove.myapplication.repository.ProductRepository
import java.util.ArrayList

class ProductViewModel : ViewModel() {

    fun addProduct(context: Context,productDetails: ArrayList<ProductDetails>){
        ProductRepository.insertProduct(context,productDetails)
    }

    fun updateProduct(context: Context,productDetails: ProductDetails){
        ProductRepository.updateProduct(context,productDetails)
    }

    fun getProducts(context: Context, catName:String):LiveData<List<ProductDetails>>{
        return ProductRepository.getProduct(context,catName)
    }

    fun getAllProducts(context: Context):LiveData<List<ProductDetails>>{
        return ProductRepository.getAllProduct(context)
    }

    fun getAllCategory(context: Context):LiveData<List<String>>{
        return ProductRepository.getCategory(context)
    }
}