package br.com.helpdev.tryauth.core.entities

import java.util.*

data class Cart(val uuid: UUID, val products: List<CartProduct>){

    fun addItems(newProducts: List<CartProduct>): Cart {
        val updatedProducts = products.toMutableList()
        updatedProducts.addAll(newProducts)
        return Cart(uuid, updatedProducts)
    }

}