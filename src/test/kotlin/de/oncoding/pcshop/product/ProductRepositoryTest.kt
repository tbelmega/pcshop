package de.oncoding.pcshop.product

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
@DataJpaTest
class ProductRepositoryTestJUnit {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    fun `saves product in database and loads by id`() {
        // given
        val productToSave = Product("1", "Ryzen 7 2700X", "AMD")

        // when
        productRepository.save(productToSave)

        // then
        val loadedProduct = productRepository.getOne("1")
        assertEquals(productToSave, loadedProduct)
    }

    @Test
    fun `loads all products from database (1)`() {
        // given
        val product1 = productRepository.save(Product("1", "Ryzen 7 2700X", "AMD"))
        val product2 = productRepository.save(Product("2", "Core i9 9900K", "Intel"))

        // when
        val products = productRepository.findAll()

        // then
        assertTrue(products.contains(product1))
        assertTrue(products.contains(product2))
        assertEquals(2, products.size)
    }

    @Test
    fun `loads all products from database (2)`() {
        // given
        val product1 = productRepository.save(Product("1", "Ryzen 7 2700X", "AMD"))
        val product2 = productRepository.save(Product("2", "Core i9 9900K", "Intel"))

        // when
        val products = productRepository.findAll()

        // then
        assertTrue("Loaded products should contain product 1", products.contains(product1))
        assertTrue("Loaded products should contain product 2", products.contains(product2))
        assertEquals(2, products.size)
    }

    @Test
    fun `loads all products from database (3)`() {
        // given
        val product1 = productRepository.save(Product("1", "Ryzen 7 2700X", "AMD"))
        val product2 = productRepository.save(Product("2", "Core i9 9900K", "Intel"))

        // when
        val products = productRepository.findAll()

        // then
        assertTrue("Loaded products should contain excatly product 1 and 2",
                containsExactlyInAnyOrder(products, product1, product2)
        )
    }

    private fun containsExactlyInAnyOrder(actual: List<Product>, vararg expected: Product): Boolean {
        return (actual.size == expected.size)
                && actual.containsAll(expected.asList())
                && expected.asList().containsAll(actual)
    }
}


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
@DataJpaTest
class ProductRepositoryTestHamcrest {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    fun `saves product in database and loads by id`() {
        // given
        val productToSave: Product = Product("1", "Ryzen 7 2700X", "AMD")

        // when
        productRepository.save(productToSave)

        // then
        val loadedProduct: Product = productRepository.getOne("1")
        assertThat(loadedProduct, equalTo(productToSave))
    }

    @Test
    fun `loads all products from database`() {
        // given
        val product1 = productRepository.save(Product("1", "Ryzen 7 2700X", "AMD"))
        val product2 = productRepository.save(Product("2", "Core i9 9900K", "Intel"))

        // when
        val products = productRepository.findAll()

        // then
        assertThat(products, containsInAnyOrder(product1, product2)) // hamcrest containsInAnyOrder corresponds to assertJs containsExactlyInAnyOrder, while hamcrest hasItems corresponds to assertJ contains
    }
}

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
@DataJpaTest
class ProductRepositoryTestAssertJ {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    fun `saves product in database and loads by id`() {
        // given
        val productToSave = Product("1", "Ryzen 7 2700X", "AMD")

        // when
        productRepository.save(productToSave)

        // then
        val loadedProduct = productRepository.getOne("1")
        assertThat(loadedProduct).isEqualTo(productToSave)
    }

    @Test
    fun `loads all products from database`() {
        // given
        val product1 = productRepository.save(Product("1", "Ryzen 7 2700X", "AMD"))
        val product2 = productRepository.save(Product("2", "Core i9 9900K", "Intel"))

        // when
        val products = productRepository.findAll()

        // then
        assertThat(products).containsExactlyInAnyOrder(product1, product2)
    }
}