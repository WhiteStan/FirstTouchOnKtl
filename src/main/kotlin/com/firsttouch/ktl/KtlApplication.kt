package com.firsttouch.ktl

import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(exclude = [R2dbcAutoConfiguration::class])
@EnableTransactionManagement
@ImportAutoConfiguration(JooqAutoConfiguration::class)
class KtlApplication

fun main(args: Array<String>) {
	runApplication<KtlApplication>(*args)
}
