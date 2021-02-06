package com.srjlove.myapplication.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.srjlove.myapplication.db.ProductDatabase
import com.srjlove.myapplication.model.ProductDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.ArrayList

class ProductRepository {
    companion object {

        var productDatabase: ProductDatabase? = null

        var productDetails: LiveData<ProductDetails>? = null

        fun initializeDB(context: Context): ProductDatabase {
            return ProductDatabase.getDatabaseClient(context)
        }

        fun insertProduct(context: Context, productDetails: ArrayList<ProductDetails>) {
            productDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                productDatabase!!.productDao().insertProduct(productDetails)
            }
        }

        fun updateProduct(context: Context, productDetails: ProductDetails) {
            productDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                productDatabase!!.productDao().updateProduct(productDetails)
            }
        }

        fun getProduct(context: Context, catName:String):LiveData<List<ProductDetails>> {
            productDatabase = initializeDB(context)
            return productDatabase!!.productDao().getProducts(catName)
        }

        fun getAllProduct(context: Context, ):LiveData<List<ProductDetails>> {
            productDatabase = initializeDB(context)
            return productDatabase!!.productDao().getAllProducts()
        }

        fun getCategory(context: Context):LiveData<List<String>> {
            productDatabase = initializeDB(context)
            return productDatabase!!.productDao().getCategory()
        }


    }
}