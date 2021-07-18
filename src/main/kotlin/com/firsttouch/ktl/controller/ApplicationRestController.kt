package com.firsttouch.ktl.controller

import com.firsttouch.ktl.model.CitizenDTO
import com.firsttouch.ktl.service.CitizenService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/citizens")
class ApplicationRestController (private val citizenService: CitizenService) {

    @GetMapping
    suspend fun findAll() = citizenService.findAll()

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Int) = citizenService.findById(id)

    @PostMapping
    suspend fun insert(@RequestBody req: CitizenDTO) :CitizenDTO? = citizenService.insert(req)

    @PutMapping
    suspend fun update(@RequestBody req: CitizenDTO) = citizenService.update(req)

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Int) = citizenService.delete(id)
}