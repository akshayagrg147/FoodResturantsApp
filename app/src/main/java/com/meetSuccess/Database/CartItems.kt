package com.meetSuccess.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "CartItems")
data class CartItems(
        @PrimaryKey()
        val id:Long,

        val strCategory: String,

        val strCategoryThumb: String,



        var strCategoryDescription: String

)


