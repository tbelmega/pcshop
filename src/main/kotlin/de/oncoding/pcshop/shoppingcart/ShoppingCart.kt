package de.oncoding.pcshop.shoppingcart

import java.math.BigDecimal

class ShoppingCart {
    private val positions: MutableSet<ShoppingCartPosition> = HashSet()

    fun add(shoppingCartPosition: ShoppingCartPosition) {
        this.positions.add(shoppingCartPosition)
    }

    fun totalAmount(): BigDecimal {
        return this.positions
                .map {
                    it.priceInEuros//.times(BigDecimal(it.amount)) //commented out to make tests fail
                }
                .fold(BigDecimal.ZERO, BigDecimal::add)
    }

}

data class ShoppingCartPosition(
        val productName: String,
        val amount: Long,
        val priceInEuros: BigDecimal
)
