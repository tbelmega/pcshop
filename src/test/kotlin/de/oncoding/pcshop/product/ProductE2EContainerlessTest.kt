package de.oncoding.pcshop.product

import com.fasterxml.jackson.databind.ObjectMapper
import de.oncoding.pcshop.common.AuditLoggerClient
import de.oncoding.pcshop.security.AuthService
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.core.Flyway
import org.junit.*
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito.mock
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import javax.persistence.EntityManager
import javax.persistence.Persistence


class ProductE2EContainerlessTest {

    private lateinit var flyway: Flyway
    private lateinit var entityManager: EntityManager
    private lateinit var repository: ProductRepository

    private val authService = mock(AuthService::class.java)
    private val auditLoggerClient = mock(AuditLoggerClient::class.java)
    private lateinit var client: MockMvc

    @Before
    fun migrate() {
        flyway = Flyway.configure()
                .dataSource("jdbc:h2:mem:test/pcshop_test_db;DB_CLOSE_DELAY=-1", null, null)
                .load()
        flyway.migrate()

        val emFactory = Persistence.createEntityManagerFactory("TestDS")
        entityManager = emFactory.createEntityManager()

        repository = JpaRepositoryFactory(entityManager).getRepository(ProductRepository::class.java)

        val controller = ProductController(repository, auditLoggerClient, authService)
        this.client = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @After
    fun clean() {
        flyway.clean()
    }


    @Test
    fun `create new product - success - return 201 CREATED`() {
        //given
        given(authService.isProductAdmin()).willReturn(true)
        val createProductRequest = CreateProductRequest("ROG Strix Z390-F Gaming Mainboard", "ASUS")

        // when
        client.perform(
                post("/api/v1/products")
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .content(createProductRequest.toJson()))

                //then
                .andExpect(status().`is`(201))

        //then
        assertThat(repository.findByProductName(createProductRequest.productName)).hasSize(1)

        verify(auditLoggerClient).logProductCreated(createProductRequest)
    }
}

fun Any.toJson(): String {
    return ObjectMapper().writeValueAsString(this)
}