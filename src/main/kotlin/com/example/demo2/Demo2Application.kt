package com.example.demo2

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Demo2Application

fun main(args: Array<String>) {
    runApplication<Demo2Application>(*args)
}

@Bean
fun init(productRepository: ProductRepository) = CommandLineRunner {
    val product = Product(name = "Apple", price = 1000)
    productRepository.save(product)
}