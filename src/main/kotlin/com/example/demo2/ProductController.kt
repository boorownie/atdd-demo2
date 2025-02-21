package com.example.demo2

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping("/{name}")
    fun getProduct(@PathVariable name: String): Map<String, Any> {
        val product = productRepository.findByName(name)
            ?: throw RuntimeException("Product not found")
        return mapOf(
            "name" to product.name,
            "price" to product.price
        )
    }
}