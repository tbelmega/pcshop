package de.oncoding.pcshop.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
@DataJpaTest
class ProductRepositoryTest {

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