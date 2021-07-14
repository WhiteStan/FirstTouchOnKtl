package com.firsttouch.ktl

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.repository.CitizenRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.net.URI


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KtlApplicationTests {

	@Autowired
	lateinit var client: TestRestTemplate

	@Autowired
	lateinit var citizenRepository: CitizenRepository

	@AfterEach
	fun tearDown() = citizenRepository.removeAll()

	@Test
	fun `test that service saves and returns saved citizen`() {
		val addedCitizenResponse = client.postForEntity<CitizenDTO>(URI("/v1/citizens"),
				CitizenDTO(firstName = "testFirstName", lastName = "testLastName", phone = "testPhone"),
				CitizenDTO::class.java)

		assertNotNull(addedCitizenResponse)
		assertNotNull(addedCitizenResponse.body)
		assertEquals(HttpStatus.OK, addedCitizenResponse.statusCode)

		val returnedCitizenResponse = client.getForEntity("v1/citizens/{${addedCitizenResponse.body?.id}}",
				CitizenDTO::class.java)

		assertNotNull(returnedCitizenResponse)
		assertNotNull(returnedCitizenResponse.body)
		assertEquals(addedCitizenResponse.body, returnedCitizenResponse.body)
	}

	@Test
	fun `test that service removes citizen`() {
		val addedCitizenResponse = client.postForEntity(URI("/v1/citizens"),
				CitizenDTO(firstName = "testFirstName", lastName = "testLastName", phone = "testPhone"),
				CitizenDTO::class.java)

		assertNotNull(addedCitizenResponse)
		assertNotNull(addedCitizenResponse.body)
		assertEquals(HttpStatus.OK, addedCitizenResponse.statusCode)

		client.delete(URI("/v1/citizens/{${addedCitizenResponse.body?.id}}"))

		val returnedCitizenResponse = client.getForEntity("v1/citizens/{${addedCitizenResponse.body?.id}}",
				CitizenDTO::class.java)

		assertNotNull(returnedCitizenResponse)
		assertNull(returnedCitizenResponse.body)
		assertEquals(HttpStatus.OK, returnedCitizenResponse.statusCode)
	}

	@Test
	fun `test that service updates citizen`() {
		val addedCitizenResponse = client.postForEntity(URI("/v1/citizens"),
				CitizenDTO(firstName = "testFirstName", lastName = "testLastName", phone = "testPhone"),
				CitizenDTO::class.java)

		assertNotNull(addedCitizenResponse)
		assertNotNull(addedCitizenResponse.body)
		assertEquals(HttpStatus.OK, addedCitizenResponse.statusCode)

		val updatedCitizen = addedCitizenResponse.body?.copy(firstName = "newName")

		client.put("/v1/citizens", updatedCitizen)

		val returnedCitizenResponse = client.getForEntity("v1/citizens/{${addedCitizenResponse.body?.id}}",
				CitizenDTO::class.java)

		assertNotNull(returnedCitizenResponse)
		assertNotNull(returnedCitizenResponse.body)
		assertEquals(HttpStatus.OK, returnedCitizenResponse.statusCode)
		assertEquals(addedCitizenResponse.body, updatedCitizen)
	}
}
