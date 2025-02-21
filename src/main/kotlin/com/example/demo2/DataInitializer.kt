package com.example.demo2

import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(private val productRepository: ProductRepository) : CommandLineRunner {
    @Transactional
    override fun run(vararg args: String?) {
        productRepository.save(Product(name = "Apple", price = 1000))
        println("✅ 초기 데이터 저장 성공")
    }
}
