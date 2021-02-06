package com.srjlove.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.srjlove.myapplication.dao.ProductDao
import com.srjlove.myapplication.model.ProductDetails

@Database(entities = [ProductDetails::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {

        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabaseClient(context: Context): ProductDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE =
                    Room.databaseBuilder(context, ProductDatabase::class.java, "PRODUCT_DATABASE")
                        .fallbackToDestructiveMigration().build()

                return INSTANCE!!

            }
        }

    }

}