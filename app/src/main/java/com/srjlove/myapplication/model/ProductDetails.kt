package com.srjlove.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDetails(
    @PrimaryKey(autoGenerate = true) val pId: Int,
    @ColumnInfo(name = "product_name") var productName: String?,
    @ColumnInfo(name = "cat_name") val catName: String?
)