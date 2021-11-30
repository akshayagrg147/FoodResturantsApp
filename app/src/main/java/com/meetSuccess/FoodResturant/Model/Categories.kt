package com.meetSuccess.FoodResturant.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("categories") @Expose
    public var categories: List<Category>


) {


    data class Category(
        @SerializedName("idCategory")
        private val idCategory: String? = null,
        @SerializedName("strCategory")
        private val strCategory: String? = null,

        @SerializedName("strCategoryThumb")
        private val strCategoryThumb: String? = null,

        @SerializedName("strCategoryDescription")
        private var strCategoryDescription: String? = null
    )
    {
        fun getIdCategory(): String? {
            return idCategory
        }



        fun getStrCategory(): String? {
            return strCategory
        }



        fun getStrCategoryThumb(): String? {
            return strCategoryThumb
        }



        fun getStrCategoryDescription(): String? {
            return strCategoryDescription
        }

        fun setStrCategoryDescription(strCategoryDescription: String?) {
            this.strCategoryDescription = strCategoryDescription
        }
    }

}


