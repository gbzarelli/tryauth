package br.com.helpdev.tryauth.core

import br.com.helpdev.tryauth.core.entities.Cart
import br.com.helpdev.tryauth.core.entities.CartProduct
import br.com.helpdev.tryauth.core.port.CartRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CartUseCase(private val cartRepository: CartRepository) {

    fun getCartFromId(uuid: UUID): Cart? {
        return cartRepository.get(uuid)
    }

    fun createCartWithProducts(products: List<CartProduct>): UUID {
        return cartRepository.create(products).uuid
    }

    fun addItemToCart(uuid: UUID, products: List<CartProduct>): Cart? {
        val cart = cartRepository.get(uuid) ?: return null
        val updatedCart = cart.addItems(products)
        cartRepository.update(updatedCart)
        return updatedCart
    }
}