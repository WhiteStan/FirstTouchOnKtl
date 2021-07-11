package com.firsttouch.ktl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(exclude = [R2dbcAutoConfiguration::class])
@EnableTransactionManagement
class KtlApplication

fun main(args: Array<String>) {
	runApplication<KtlApplication>(*args)
}
