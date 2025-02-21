package com.example.demo2

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductIntegrationTest {

    @Autowired
    private lateinit var productRepository: ProductRepository

    private val restTemplate = TestRestTemplate()

    companion object {
        @Container
        @ServiceConnection
        val mysql = MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("testdb")
            withUsername("root")
            withPassword("root")
        }
    }

    @Test
    fun `B 애플리케이션에서 상품 조회`() {
        // given
        val productName = "Apple"

        // when
        val response: ResponseEntity<Map<*, *>> = restTemplate.getForEntity(
            "http://localhost:8080/products/$productName",
            Map::class.java
        )

        // then
        assertEquals(HttpStatus.OK, response.statusCode)

        val responseBody = response.body!!
        assertEquals("Apple", responseBody["name"])
        assertEquals(1000, responseBody["price"])
    }
}