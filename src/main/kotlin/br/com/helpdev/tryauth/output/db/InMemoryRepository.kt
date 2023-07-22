package br.com.helpdev.tryauth.output.db

import br.com.helpdev.tryauth.core.entities.Cart
import br.com.helpdev.tryauth.core.entities.CartProduct
import br.com.helpdev.tryauth.core.entities.Product
import br.com.helpdev.tryauth.core.port.CartRepository
import br.com.helpdev.tryauth.core.port.ProductRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Service
final class InMemoryRepository : CartRepository, ProductRepository {

    companion object {
        private val productIds = AtomicInteger()
        private val carts = ConcurrentHashMap<UUID, Cart>()
        private val products = ConcurrentHashMap<Int, Product>()
    }

    override fun get(uuid: UUID): Cart? {
        return carts[uuid]
    }

    override fun create(products: List<CartProduct>): Cart {
        val cart = Cart(UUID.randomUUID(), products)
        carts[cart.uuid] = cart
        return cart
    }

    override fun update(cart: Cart) {
        carts[cart.uuid] = cart
    }

    override fun get(): List<Product> {
        return products.elements().toList()
    }

    override fun insert(product: Product): Product {
        val nextProductId = productIds.incrementAndGet()
        val newProduct = Product.withId(nextProductId, product)
        products[nextProductId] = newProduct
        return newProduct
    }
}