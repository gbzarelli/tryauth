package br.com.helpdev.tryauth.core.port

import br.com.helpdev.tryauth.core.entities.Product

interface ProductRepository {
    fun get(): List<Product>
    fun insert(product: Product): Product
}