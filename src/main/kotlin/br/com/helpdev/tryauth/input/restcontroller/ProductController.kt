package br.com.helpdev.tryauth.input.restcontroller

import br.com.helpdev.tryauth.core.ProductUseCase
import br.com.helpdev.tryauth.core.entities.Product
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductController(val productUseCase: ProductUseCase) {

    @PreAuthorize("hasAuthority('SCOPE_tryauth:read')")
    @GetMapping
    fun list(): List<Product> {
        return productUseCase.listAllProducts()
    }

    @PreAuthorize("hasAuthority('SCOPE_tryauth:write')")
    @PostMapping
    fun add(@RequestBody product: Product): Product {
        return productUseCase.addProduct(product)
    }

}