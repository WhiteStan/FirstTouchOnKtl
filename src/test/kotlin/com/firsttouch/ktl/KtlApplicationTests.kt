package com.firsttouch.ktl

import com.firsttouch.ktl.repository.CitizenRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KtlApplicationTests {

	@Autowired
	lateinit var client: TestRestTemplate

	@Autowired
	lateinit var citizenRepository: CitizenRepository

	@BeforeEach
	fun setUp() {
	}

	@Test
	fun contextLoads() {
	}

}
