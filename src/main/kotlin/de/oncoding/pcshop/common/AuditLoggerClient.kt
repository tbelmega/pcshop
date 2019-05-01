package de.oncoding.pcshop.common

import de.oncoding.pcshop.product.CreateProductRequest
import org.springframework.stereotype.Service

@Service
class AuditLoggerClient {
    fun logProductCreated(any: CreateProductRequest) {

    }
}