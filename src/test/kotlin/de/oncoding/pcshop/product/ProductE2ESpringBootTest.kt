package de.oncoding.pcshop.product

import de.oncoding.pcshop.common.AuditLoggerClient
import de.oncoding.pcshop.security.AuthService
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
class ProductE2ESpringBootTest {

    @Autowired
    lateinit var client: TestRestTemplate

    @Autowired
    lateinit var repository: ProductRepository

    @MockBean
    lateinit var authService: AuthService

    @MockBean
    lateinit var auditLoggerClient: AuditLoggerClient


    @Before
    @After
    fun cleanUpDb() {
        repository.deleteAll()
    }


    @Test
    fun `create new product - success - return 201 CREATED`() {
        //given
        given(authService.isProductAdmin()).willReturn(true)
        val createProductRequest = CreateProductRequest("ROG Strix Z390-F Gaming Mainboard", "ASUS")

        // when
        val response = client.postForEntity(
                "/api/v1/products",
                createProductRequest,
                Void::class.java)

        //then
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.body).isNull()

        assertThat(repository.findByProductName(createProductRequest.productName)).hasSize(1)

        verify(auditLoggerClient).logProductCreated(createProductRequest)
    }
}

