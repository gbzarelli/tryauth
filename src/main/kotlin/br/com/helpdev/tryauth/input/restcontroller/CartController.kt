package br.com.helpdev.tryauth.input.restcontroller

import br.com.helpdev.tryauth.core.CartUseCase
import br.com.helpdev.tryauth.core.entities.Cart
import br.com.helpdev.tryauth.core.entities.CartProduct
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/cart")
class CartController(val cartUseCase: CartUseCase) {

    companion object {
        val CART_NOT_FOUND: ResponseStatusException = ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Cart not found"
        )
    }

    @PreAuthorize("hasAuthority('SCOPE_tryauth:read')")
    @GetMapping("/{uuid}")
    fun get(@PathVariable uuid: UUID): Cart {
        return cartUseCase.getCartFromId(uuid) ?: throw CART_NOT_FOUND
    }

    @PreAuthorize("hasAuthority('SCOPE_tryauth:write')")
    @PostMapping
    fun create(@RequestBody products: CartProductsDto): UUID {
        return cartUseCase.createCartWithProducts(products.products)
    }

    @PreAuthorize("hasAuthority('SCOPE_tryauth:write')")
    @PutMapping("/{uuid}")
    fun addItem(@PathVariable uuid: UUID, @RequestBody products: CartProductsDto): Cart {
        return cartUseCase.addItemToCart(uuid, products.products) ?: throw CART_NOT_FOUND
    }

}

data class CartProductsDto(val products: List<CartProduct>)