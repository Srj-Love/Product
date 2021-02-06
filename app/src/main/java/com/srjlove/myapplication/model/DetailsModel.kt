package com.srjlove.myapplication.model

data class DetailsModel(
    val viewType:Int, val category:String?= null, var productDetails: ProductDetails?= null
)
