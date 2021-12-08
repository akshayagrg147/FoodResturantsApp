package com.meetSuccess.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCartItem(contact: CartItems)
    @Delete
   suspend fun deleteCartItem(contact: CartItems)
    @Update
   suspend fun updateCartItem(contact: CartItems)


  @Query("SELECT COUNT(*) FROM CartItems")
     fun getCount(): LiveData<Int>
    @Query("SELECT * FROM CartItems")
    fun getContact():LiveData<List<CartItems>>
//    @Query("SELECT ItemCount FROM CartItems where id=:idd")
//    fun getProductBasedId( idd:Long):LiveData<Int>
}