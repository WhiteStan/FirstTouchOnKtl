package com.firsttouch.ktl.controller

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.service.CitizenService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/records")
class ApplicationRestController (private val citizenService: CitizenService) {

    @GetMapping
    fun findAll() = citizenService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) = citizenService.findById(id)

    @PutMapping
    fun insert(@RequestBody req: CitizenDTO) = citizenService.insert(req)

    @PostMapping
    fun update(@RequestBody req: CitizenDTO) = citizenService.update(req)
}