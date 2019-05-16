package de.oncoding.pcshop.shoppingcart

import org.junit.Assert.assertEquals
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal

class ShoppingCartTestJUnit {

    @Test
    fun `totalAmount() for empty cart is zero`() {
        // given
        val cart = ShoppingCart()

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertEquals(BigDecimal.ZERO, totalAmount)
    }

    @Test
    fun `totalAmount() for 1 item returns its price`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 1, BigDecimal(300)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertEquals(BigDecimal(300), totalAmount)
    }

    @Test
    fun `totalAmount() for the same item twice returns two times its price`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 2, BigDecimal(300)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertEquals(BigDecimal(600), totalAmount)
    }

    @Test
    fun `totalAmount() for different positions adds their amounts`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 1, BigDecimal(300)))
        cart.add(ShoppingCartPosition("Core i9 9900K", 2, BigDecimal(500)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertEquals(BigDecimal(1300), totalAmount)
    }
}

class ShoppingCartTestHamcrest {

    @Test
    fun `totalAmount() for for empty cart is zero`() {
        // given
        val cart = ShoppingCart()

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount, equalTo(BigDecimal.ZERO))
    }

    @Test
    fun `totalAmount() for 1 item returns its price`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 1, BigDecimal(300)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount, equalTo(BigDecimal(300)))
    }

    @Test
    fun `totalAmount() for the same item twice returns two times its price`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 2, BigDecimal(300)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount, equalTo(BigDecimal(600)))
    }

    @Test
    fun `totalAmount() for different positions adds their amounts`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 1, BigDecimal(300)))
        cart.add(ShoppingCartPosition("Core i9 9900K", 2, BigDecimal(500)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount, equalTo(BigDecimal(1300)))
    }
}

class ShoppingCartTestAssertJ {

    @Test
    fun `totalAmount() for for empty cart is zero`() {
        // given
        val cart = ShoppingCart()

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun `totalAmount() for 1 item returns its price`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 1, BigDecimal(300)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount).isEqualTo(BigDecimal(300))
    }

    @Test
    fun `totalAmount() for the same item twice returns two times its price`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 2, BigDecimal(300)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount).isEqualTo(BigDecimal(600))
    }

    @Test
    fun `totalAmount() for different positions adds their amounts`() {
        // given
        val cart = ShoppingCart()
        cart.add(ShoppingCartPosition("Ryzen 7 2700X", 1, BigDecimal(300)))
        cart.add(ShoppingCartPosition("Core i9 9900K", 2, BigDecimal(500)))

        // when
        val totalAmount = cart.totalAmount()

        // then
        assertThat(totalAmount).isEqualTo(BigDecimal(1300))
    }

}

