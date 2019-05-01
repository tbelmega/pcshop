package de.oncoding.pcshop.product

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class Product (
        @Id
        val id: String,
        val productName: String,
        val manufacturer: String
)