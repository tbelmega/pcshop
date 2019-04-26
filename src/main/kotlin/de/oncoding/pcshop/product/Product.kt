package de.oncoding.pcshop.product

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Product (
        @Id
        val id: String,
        val productName: String,
        val manufacturer: String
)