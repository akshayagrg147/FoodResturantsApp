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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddressItem(address: AddressItems)
    @Query("SELECT * FROM AddressItems")
    fun getAllAddress():LiveData<List<AddressItems>>

    @Query("SELECT SUM(productPrice) FROM CartItems")
    fun getTotalPrice():LiveData<Int>

  @Query("SELECT COUNT(*) FROM CartItems")
     fun getTotalProductItems(): LiveData<Int>
    @Query("SELECT * FROM CartItems")
    fun getContact():LiveData<List<CartItems>>
    @Query("SELECT totalCount FROM CartItems where ProductIdNumber=:ProductIdNumber")
    fun getProductBasedIdCount( ProductIdNumber:String):Int
}