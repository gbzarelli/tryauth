package br.com.helpdev.tryauth.core

import br.com.helpdev.tryauth.core.entities.Product
import br.com.helpdev.tryauth.core.port.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductUseCase(private val productRepository: ProductRepository) {

    fun listAllProducts(): List<Product>{
        return productRepository.get()
    }

    fun addProduct(product: Product): Product {
        return productRepository.insert(product)
    }
}