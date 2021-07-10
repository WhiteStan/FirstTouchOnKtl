package com.firsttouch.ktl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KtlApplication

fun main(args: Array<String>) {
	runApplication<KtlApplication>(*args)
}
