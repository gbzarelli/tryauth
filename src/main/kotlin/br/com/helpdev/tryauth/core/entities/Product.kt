package br.com.helpdev.tryauth.core.entities

import java.math.BigDecimal

data class Product(val id: Int=0, val name: String, val value: BigDecimal) {

    companion object {
        fun withId(id: Int, product: Product): Product {
            return Product(id,product.name,product.value)
        }
    }

}