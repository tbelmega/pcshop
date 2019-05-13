package de.oncoding.pcshop.product

import de.oncoding.pcshop.common.AuditLoggerClient
import de.oncoding.pcshop.security.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ProductController(
        val productRepository: ProductRepository,
        val auditLoggerClient: AuditLoggerClient,
        val authService: AuthService
) {

    @PostMapping("api/v1/products")
    fun createProduct(
            @RequestBody request: CreateProductRequest
    ): ResponseEntity<Void> {
        if (authService.isProductAdmin().not())
            return ResponseEntity.notFound().build()

        val product = Product(
                id = UUID.randomUUID().toString(),
                productName = request.productName,
                manufacturer = request.manufacturer
        )
        productRepository.save(product)

        auditLoggerClient.logProductCreated(request)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}


data class CreateProductRequest(
        val productName: String = "",
        val manufacturer: String = ""
)