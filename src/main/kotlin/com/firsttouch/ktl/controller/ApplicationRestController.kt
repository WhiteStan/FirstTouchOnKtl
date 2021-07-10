package com.firsttouch.ktl.controller

import com.firsttouch.ktl.service.RecordsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/records")
class ApplicationRestController (private val recordsService: RecordsService) {

}