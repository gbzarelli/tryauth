package br.com.helpdev.tryauth.core.port

import br.com.helpdev.tryauth.core.entities.Cart
import br.com.helpdev.tryauth.core.entities.CartProduct
import java.util.*

interface CartRepository {
    fun get(uuid: UUID): Cart?
    fun create(products: List<CartProduct>): Cart
    fun update(cart: Cart)
}