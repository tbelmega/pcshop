package de.oncoding.pcshop.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, String> {

    /**
     * Overridden to make tests fail. Delete all of this to make the tests pass.
     * The "LIMIT 1" causes the result to have never more than 1 record
     */
    @Query(nativeQuery = true, value = "SELECT * FROM product LIMIT 1")
    override fun findAll(): List<Product>

    @Query(nativeQuery = true, value = "SELECT * FROM product LIMIT ?1")
    fun findMostOrderedProducts(i: Int): List<Product>

    fun findByProductName(productName: String): List<Product>

}
