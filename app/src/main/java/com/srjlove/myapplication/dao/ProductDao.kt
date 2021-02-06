package com.srjlove.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.srjlove.myapplication.model.ProductDetails
import java.util.ArrayList

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ArrayList<ProductDetails>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: ProductDetails)


    @Query("select distinct cat_name from ProductDetails")
    fun getCategory() : LiveData<List<String>>

    @Query("select * from ProductDetails where cat_name = :catName")
    fun getProducts(catName:String) : LiveData<List<ProductDetails>>

    @Query("select * from ProductDetails")
    fun getAllProducts() : LiveData<List<ProductDetails>>



}