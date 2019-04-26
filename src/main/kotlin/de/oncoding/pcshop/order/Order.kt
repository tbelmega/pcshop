package de.oncoding.pcshop.order

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "product_order")
data class Order (
        @Id
        val id: String,
        val amount: Long,
        val productId: Long,
        val orderDate: LocalDate,
        val customerId: Long
)