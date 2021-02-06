package com.srjlove.myapplication.callback

import com.srjlove.myapplication.model.ProductDetails

interface ProductClickListener {
    fun onCLickProduct(position:Int,productDetails: ProductDetails)
}