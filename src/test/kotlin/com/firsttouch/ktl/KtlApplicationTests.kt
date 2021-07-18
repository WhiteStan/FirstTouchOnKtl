package com.firsttouch.ktl

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.repository.CitizenRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import java.net.URI


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KtlApplicationTests {

    @Autowired
    lateinit var client: TestRestTemplate

    @Autowired
    lateinit var citizenRepository: CitizenRepository

    @AfterEach
    fun tearDown() = runBlocking { citizenRepository.removeAll() }

    @Test
    fun `test that service saves new citizen`() {
        runBlocking {
            val addedCitizenResponse = client.postForEntity<CitizenDTO>(URI("/v1/citizens"),
                    CitizenDTO(firstName = "testFirstName", lastName = "testLastName", phone = "testPhone"),
                    CitizenDTO::class.java)

            assertNotNull(addedCitizenResponse)
            assertNotNull(addedCitizenResponse.body)
            assertEquals(HttpStatus.OK, addedCitizenResponse.statusCode)

            val savedCitizen = citizenRepository.findById(addedCitizenResponse.body?.id!!)

            assertNotNull(savedCitizen)
            assertEquals(addedCitizenResponse.body, savedCitizen)
        }
    }

    @Test
    fun `test that service returns saved citizen`() {
        runBlocking {
            val savedCitizen = citizenRepository.insert(CitizenDTO(firstName = "testFirstName", lastName = "testLastName", phone = "testPhone"))

            assertNotNull(savedCitizen)

            val id = savedCitizen?.id

            val returnedCitizenResponse = client.getForEntity("/v1/citizens/$id",
                    CitizenDTO::class.java)

            assertNotNull(returnedCitizenResponse)
            assertEquals(HttpStatus.OK, returnedCitizenResponse.statusCode)
            assertNotNull(returnedCitizenResponse.body)
            assertEquals(savedCitizen, returnedCitizenResponse.body)
        }
    }

    @Test
    fun `test that service removes citizen`() {
        runBlocking {
            val savedCitizen = citizenRepository.insert(CitizenDTO(firstName = "testFirstName", lastName = "testLastName", phone = "testPhone"))

            assertNotNull(savedCitizen)

            val id = savedCitizen?.id

            client.delete(URI("/v1/citizens/$id"))

            val savedCitizenAfterDeletion = citizenRepository.findById(id!!)

            assertNull(savedCitizenAfterDeletion)
        }
    }

    @Test
    fun `test that service updates citizen`() {
        runBlocking {
            val savedCitizen = citizenRepository.insert(CitizenDTO(firstName = "testFirstName", lastName = "testLastName", phone = "testPhone"))

            assertNotNull(savedCitizen)

            val id = savedCitizen?.id

            val updatedCitizenRequest = savedCitizen?.copy(firstName = "newName")

            val updatedCitizenResponse = client.put("/v1/citizens", updatedCitizenRequest)

            assertNotNull(updatedCitizenResponse)

            val returnedCitizenResponse = citizenRepository.findById(id!!)

            assertNotNull(returnedCitizenResponse)
            assertEquals(returnedCitizenResponse, updatedCitizenRequest)
        }
    }
}
